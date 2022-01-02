package pkgImgSeek;

import java.util.LinkedList;
import java.util.List;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

import pkgBean.ImgSeekBean;

public class ImgSeek {

    private static float nndrRatio = 0.8f;//�������üȶ�ֵΪ0.7����ֵ�����е���



    public float getNndrRatio() {
        return nndrRatio;
    }

    public void setNndrRatio(float nndrRatio) {
        ImgSeek.nndrRatio = nndrRatio;
    }


    /**
     * Ѱͼ����
     * @param templateFilePath ģ��ͼ�ļ�·��
     * @param originalFilePath ԭͼ�ļ�·��
     * @param isTest �Ƿ�Ϊ����ģʽ����������Ѱͼ�������ͼƬ
     * @return ����ImgSeekBean���󣬰���ͼ��ƥ�����������Ϣ
     */
    public static ImgSeekBean findImg(String templateFilePath,String originalFilePath,boolean isTest){
    	ImgSeekBean isb = new ImgSeekBean();
    	try{
    		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    		//������ʱ�ļ��в���ͼƬ�洢·��
    		String tempPath = "./res/pics/pic_temp/";
    		//��ȡͼƬת��Ϊ����
    		Mat templateImage = Highgui.imread(templateFilePath, Highgui.CV_LOAD_IMAGE_COLOR);
    		Mat originalImage = Highgui.imread(originalFilePath, Highgui.CV_LOAD_IMAGE_COLOR);
            MatOfKeyPoint templateKeyPoints = new MatOfKeyPoint();
            //ָ���������㷨SURF
            FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SIFT);
            //��ȡģ��ͼ��������
            featureDetector.detect(templateImage, templateKeyPoints);
            //��ȡģ��ͼ��������
            MatOfKeyPoint templateDescriptors = new MatOfKeyPoint();
            DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);
            descriptorExtractor.compute(templateImage, templateKeyPoints, templateDescriptors);
            isb.setTempKeyPointsNum((int)templateKeyPoints.size().height);

            //��ȡԭͼ��������
            MatOfKeyPoint originalKeyPoints = new MatOfKeyPoint();
            MatOfKeyPoint originalDescriptors = new MatOfKeyPoint();
            featureDetector.detect(originalImage, originalKeyPoints);
            descriptorExtractor.compute(originalImage, originalKeyPoints, originalDescriptors);
            
            List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();
            DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
            //Ѱ�����ƥ��
            /**
             * knnMatch���������þ����ڸ�����������������Ѱ�����ƥ��
             * ʹ��KNN-matching�㷨����K=2����ÿ��match�õ�������ӽ���descriptor��Ȼ�������ӽ�����ʹνӽ�����֮��ı�ֵ������ֵ���ڼȶ�ֵʱ������Ϊ����match��
             */
            descriptorMatcher.knnMatch(templateDescriptors, originalDescriptors, matches, 2);

            //��ƥ��������ɸѡ������distance����ɸѡ
            LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();
            matches.forEach(match -> {
                DMatch[] dmatcharray = match.toArray();
                DMatch m1 = dmatcharray[0];
                DMatch m2 = dmatcharray[1];
                if (m1.distance <= m2.distance * nndrRatio) {
                    goodMatchesList.addLast(m1);
                }
            });
            isb.setMatchKeyPointsNum(goodMatchesList.size());
            //��ƥ�����������ڵ��� 4 ��������Ϊģ��ͼ��ԭͼ�У���ƥ��ɹ�
            if (isb.getMatchKeyPointsNum() >= 4) {
                isb.setSeek(true);
                List<KeyPoint> templateKeyPointList = templateKeyPoints.toList();
                List<KeyPoint> originalKeyPointList = originalKeyPoints.toList();
                LinkedList<Point> objectPoints = new LinkedList<Point>();
                LinkedList<Point> scenePoints = new LinkedList<Point>();
                goodMatchesList.forEach(goodMatch -> {
                    objectPoints.addLast(templateKeyPointList.get(goodMatch.queryIdx).pt);
                    scenePoints.addLast(originalKeyPointList.get(goodMatch.trainIdx).pt);
                });
                MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
                objMatOfPoint2f.fromList(objectPoints);
                MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
                scnMatOfPoint2f.fromList(scenePoints);
                //ʹ�� findHomography Ѱ��ƥ���ϵĹؼ���ı任
                Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);

                /**
                 * ͸�ӱ任(Perspective Transformation)�ǽ�ͼƬͶӰ��һ���µ���ƽ��(Viewing Plane)��Ҳ����ͶӰӳ��(Projective Mapping)��
                 */
                Mat templateCorners = new Mat(4, 1, CvType.CV_32FC2);
                Mat templateTransformResult = new Mat(4, 1, CvType.CV_32FC2);
                templateCorners.put(0, 0, new double[]{0, 0});
                templateCorners.put(1, 0, new double[]{templateImage.cols(), 0});
                templateCorners.put(2, 0, new double[]{templateImage.cols(), templateImage.rows()});
                templateCorners.put(3, 0, new double[]{0, templateImage.rows()});
                //ʹ�� perspectiveTransform ��ģ��ͼ����͸�ӱ��Խ���ͼ��õ���׼ͼƬ
                Core.perspectiveTransform(templateCorners, templateTransformResult, homography);

                //�����ĸ�����
                double[] pointA = templateTransformResult.get(0, 0);
                double[] pointB = templateTransformResult.get(1, 0);
                double[] pointC = templateTransformResult.get(2, 0);
                double[] pointD = templateTransformResult.get(3, 0);
                
                //ָ��ȡ�������Ӽ��ķ�Χ
                int rowStart = (int) pointA[1];
                int rowEnd = (int) pointC[1];
                int colStart = (int) pointD[0];
                int colEnd = (int) pointB[0];
                Mat subMat = originalImage.submat(rowStart, rowEnd, colStart, colEnd);
                
                isb.setY1(rowStart);
                isb.setY2(rowEnd);
                isb.setX1(colStart);
                isb.setX2(colEnd);
                if(isTest){//����ģʽ�Ķ�������������
                	//����ģ��ͼ��������ͼƬ
                	Mat outputImage = new Mat(templateImage.rows(), templateImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);
                	Features2d.drawKeypoints(templateImage, templateKeyPoints, outputImage, new Scalar(255, 0, 0), 0);
                	MatOfDMatch goodMatches = new MatOfDMatch();
                	goodMatches.fromList(goodMatchesList);
                	Mat matchOutput = new Mat(originalImage.rows() * 2, originalImage.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);
                	Features2d.drawMatches(templateImage, templateKeyPoints, originalImage, originalKeyPoints, goodMatches, matchOutput, new Scalar(0, 255, 0), new Scalar(255, 0, 0), new MatOfByte(), 2);

                	//���-ԭͼ�е�ƥ��ͼ
                	Highgui.imwrite(tempPath+"MatchTemp.jpg", subMat);
                	//��ƥ���ͼ�����������߿����
                	Core.line(originalImage, new Point(pointA), new Point(pointB), new Scalar(0, 255, 0), 4);//�� A->B
                	Core.line(originalImage, new Point(pointB), new Point(pointC), new Scalar(0, 255, 0), 4);//�� B->C
                	Core.line(originalImage, new Point(pointC), new Point(pointD), new Scalar(0, 255, 0), 4);//�� C->D
                	Core.line(originalImage, new Point(pointD), new Point(pointA), new Scalar(0, 255, 0), 4);//�� D->A
                	//���-������ƥ�����
                	Highgui.imwrite(tempPath+"MatchProcess.jpg", matchOutput);
                	//���-ģ��ͼ��ԭͼ�е�λ��
                	Highgui.imwrite(tempPath+"MatchLocation.jpg", originalImage);
                	//���-ģ��ͼ������
                	Highgui.imwrite(tempPath+"TemplatePoints.jpg", outputImage);
                }
                if(isb.getX1()==-1)//�����������
                	isb.setSeek(false);
            } else {//ƥ�䲻�ɹ�
                isb.setSeek(false);
            }
    	}
    	catch(Exception e){
    		isb.setSeek(false);
    		e.printStackTrace();
    	}
    	return isb;
    }//findImgTest
    
   }//class ImgSeek

