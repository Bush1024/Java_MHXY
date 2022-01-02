package pkgTest;

import pkgImgSeek.ImgSeek;
import pkgBean.ImgSeekBean;

public class TestImgSeek{
    public static void main(String[] args) {
        String templateFilePath = "./res/pic/0Temp.jpg";
        String originalFilePath = "./res/pic/0Pic.jpg";
        ImgSeekBean isb = new ImgSeekBean();
        isb = ImgSeek.findImg(templateFilePath, originalFilePath,true);
        if(isb.isSeek()){
        	System.out.println("ģ��ͼ��ԭͼƥ��ɹ���");
        	System.out.println("ƥ�����Ͻ�λ��(x1="+isb.getX1()+",y1="+isb.getY1()+")");
        	System.out.println("ƥ�����½�λ��(x2="+isb.getX2()+",y2="+isb.getY2()+")");
        }
        else
        	System.out.println("ģ��ͼ��ԭͼƥ��ʧ��.");
        
        System.out.println("ģ��ͼ������������" + isb.getTempKeyPointsNum());
        System.out.println("ƥ���������������" + isb.getMatchKeyPointsNum());
    }	
}