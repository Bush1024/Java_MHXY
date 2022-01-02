package pkgAssist;

import java.awt.Color;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jacob.com.Variant;

import pkgBean.GameBean;
import pkgBean.GamePointBean;
import pkgBean.ImgSeekBean;
import pkgCalc.CalcTool;
import pkgDm.Dm2Java;
import pkgImgSeek.ImgSeek;


/**
 * �λø��������⣬��Ը������򣬰��������ĳ�ʼ�������õȺ��� 
 * @author Bush
 */
public class MhxyAssist {
	public static GameBean GB;
	
	/**
	 * �ű���ʼ��
	 */
	public static void AssistInit(int hwnd){
		
		//��Į��ʼ��
		Dm2Java.DMInit();
		//��Į����Ϸ���
		Dm2Java dm = new Dm2Java();
		if(dm.BindWindow(hwnd, "gdi", "windows", "windows", 0)==0){
			System.out.println("�󶨾��ʧ�ܣ����飡");
			return;
		}
		//ȫ�����ݳ�ʼ��
		GB = new GameBean();
		GB.setHwnd(hwnd);
		GB.setAssistStart(new Date());
		//��Ϸ���ڳ�ʼ������Ϊ��С����ָ����ڣ���������
		if(dm.GetWindowState(hwnd, 3)==1);
			dm.SetWindowState(hwnd,5 );
		
		{//��ʼ����Ϸ��������
		Variant x1 = new Variant(-1,true);
		Variant y1 = new Variant(-1,true);
		Variant x2 = new Variant(-1,true);
		Variant y2 = new Variant(-1,true);
		dm.GetWindowRect(hwnd, x1, y1, x2, y2);
		GB.setGx(x1.getInt());
		GB.setGy(y1.getInt());
		GB.setWidth((x2.getInt()-x1.getInt()));
		GB.setHeight((y2.getInt()-y1.getInt()));
		}
		{//��ʼ��״̬����
		GamePointBean gps[] = new GamePointBean[4];
		for(int i=0;i<gps.length;i++)
			gps[i] = new GamePointBean();
		gps[0].x = (int)(GB.getGx()+0.05*GB.getWidth()); gps[0].y = (int)(GB.getGy()+0.54*GB.getHeight());
		gps[1].x = (int)(GB.getGx()+0.96*GB.getWidth()); gps[1].y = (int)(GB.getGy()+0.25*GB.getHeight());
		gps[2].x = (int)(GB.getGx()+0.50*GB.getWidth()); gps[2].y = (int)(GB.getGy()+0.92*GB.getHeight());
		gps[3].x = (int)(GB.getGx()+0.97*GB.getWidth()); gps[3].y = (int)(GB.getGy()+0.68*GB.getHeight());
		for(GamePointBean p: gps)
			p.c = new Color(0x000000);
		GB.setGps(gps);
		//��ʼ��״̬Ϊ��ֹ������0��
		GB.setState("��ֹ");
		GB.setStateTime(0);
		//��ʼ������Ϊ��
		GB.setJobDoing("��");
		}
		
	}//AssistInit
	
	/**
	 * ��Ϸ״̬��أ�ע��˴���ָ��ֹ��һ�������ﾲֹ������ָ���澲ֹ
	 * ��������ĳЩ��ȫ�������ʱ�������ﴦ���ƶ�״̬Ҳ�����ж�Ϊ�Ǿ�ֹ
	 * (�ǵý���ʱ�����ʱ��)
	 */
	public static void GameMonitor(Dm2Java dm){
		//������ʱColor����
		Color cc[] = new Color[1];
		//������ʱ��,�ǵ�����
		Timer GMTimer = new Timer();
		TimerTask T_4p = new TimerTask(){
			public void run(){
				//���þ�ֹ������
				int stopCount=0;
				System.out.println("״̬��أ�===============================");
				//ȡ���ĵ���ɫֵ
				for(int i=0;i<4;i++){
					cc[0] = CalcTool.dmCToC(dm.GetColor(GB.getGps()[i].x, GB.getGps()[i].y));
					if((cc[0].getRGB()^GB.getGps()[i].c.getRGB())!=0)//����ɫ��ͬ����¼�ص���ɫ
						GB.getGps()[i].c=cc[0];
					else{
						stopCount++;
					}
				}
				//���ݼ�ص�ľ�ֹ��������״̬����
				System.out.println("��ֹ��������"+stopCount);
				System.out.println("��Ϸ״̬��"+GB.getState()+",����ʱ��:"+GB.getStateTime()/1000);
				GameState(dm,stopCount);
				
			};//run
		};//new TimerTask()
		GMTimer.schedule(T_4p, 1000,1000);
	}//GameMonitor
	
	
	/**
	 * ��Ϸ״̬�ж�,�����ص��о�ֹ�������������ж�
	 */
	public static void GameState(Dm2Java dm,int stopCount){
		switch(GB.getState()){
			case"��ֹ"://��ǰ�Ǿ�ֹ״̬
						if(stopCount==0){//û�о�ֹ�㣬˵�����ƶ�
							GB.setState("�ƶ�");
							GB.setStateTime(0);
						}//���ھ�ֹ��,��Ϊս�����棬�����״̬�̳�ʱ��
						else if(GB.getFightPoint()!=null&&dm.GetColor(GB.getFightPoint().x, GB.getFightPoint().y).equals(GB.getFightPoint().dmC)){
							GB.setState("ս��");
						}
						else{
							GB.setStateTime(GB.getStateTime()+1000);
						}
						//����ֹ�ﵽһ��ʱ�����ս��״̬��δ����¼ʱ�������ս��״̬����״μ�¼
						if(GB.getFightPoint()==null&&(GB.getStateTime()==5000)){
							System.out.println("��ֹ��5����δ��¼ս��״̬�㴥��!");
							ImgSeekBean isb = new ImgSeekBean();
							//����"ȡ���Զ�ս��"����¼"ȡ��"���ĵ�
							isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Game/Cancel.jpg");
							if(isb.isSeek()){//�ҵ���"ȡ��"����¼ս��״̬��
								GamePointBean gpb = new GamePointBean();
								gpb.x = isb.getMidX();
								gpb.y = isb.getMidY();
								gpb.dmC = dm.GetColor(gpb.x, gpb.y);
								gpb.c = CalcTool.dmCToC(gpb.dmC);
								GB.setFightPoint(gpb);
								GB.setState("ս��");//����״̬�����̳�״̬ʱ��
							}else//û�ҵ�ȡ��,ս������Ϊnull
								GB.setFightPoint(null);
						}
						//��ֹ�ﵽ15��ʱ�����е�ǰ�����ж�
						if(GB.getStateTime()==10000){
							switch(GB.getJobDoing()){
								case"��ͼ����":
									GB.setJobDoing("��");
										break;
								case"ץ������":
									if(GB.getNowGuiNum()>=10){
										GB.setJobDoing("��");
										System.out.println("һ��ץ�����");
										//Ѱ��ȷ�ϰ�ť
										ImgSeekBean isb = new ImgSeekBean();
										isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Gui/Words_Yes.jpg");
										if(isb.isSeek()){
											GB.setMapGo(false);//�ҵ�ȷ�ϾͲ��õ�ͼ�����
											dm.ClickXY(isb.getMidX(), isb.getMidY());//���ȷ��
											MhxyJob.Job_Gui(dm);//����ץ��������ܽű�
										}
									}//
									else{//����10ֻ��û�ҵ�ȷ��
										System.out.println("��ֹ��ʱ����δ�ҵ�ȷ�ϰ�ť������");
									}
										break;
							}
							System.out.println(GB.getJobDoing()+"��ɣ�����������ű���");
						}
						break;
			case"�ƶ�":
					if(stopCount==0){//û�о�ֹ�㣬˵�����ƶ�
						GB.setStateTime(GB.getStateTime()+1000);
					}//�����ھ�ֹ�㣬��Ϊս������
					else if(GB.getFightPoint()!=null&&dm.GetColor(GB.getFightPoint().x, GB.getFightPoint().y).equals(GB.getFightPoint().dmC)){
						GB.setState("ս��");
						GB.setStateTime(0);
					}
					else{//���ھ�ֹ���ҷ�ս������,����Ϊ��ֹ״̬
						GB.setState("��ֹ");
						GB.setStateTime(0);
					}
					break;
			case"ս��"://�����ж��Ƿ�Ϊս������
					if(dm.GetColor(GB.getFightPoint().x, GB.getFightPoint().y).equals(GB.getFightPoint().dmC)){
						GB.setStateTime(GB.getStateTime()+1000);
					}
					else if(stopCount==0){//û�о�ֹ�㣬����Ϊ�ƶ�
						GB.setState("�ƶ�");
						GB.setStateTime(0);
					}else{//���ھ�ֹ��,����Ϊ��ֹ
						GB.setState("��ֹ");
						GB.setStateTime(0);
					}
					break;
						
		}

			
	}//GameState
	
	/**��Ϸ���ڵ���������ʱʹ��ȫ�����
	 * @param dm 
	 * @param x ���Ͻ�x����
	 * @param y ���Ͻ�y����
	 * @param width ���ڿ��
	 * @param height ���ڸ߶�
	 */
	public static void GameWindowAdjust(Dm2Java dm,int x,int y,int width,int height){
		if(Dm2Java.isInit){
			dm.SetWindowState(GB.getHwnd(), 1);
			dm.SetWindowSize(GB.getHwnd(), width, height);
			dm.MoveWindow(GB.getHwnd(), x, y);
			GB.setGx(x);
			GB.setGy(y);
			GB.setWidth(width);
			GB.setHeight(height);
			//���һ�´��ڣ�����ģ��״̬(���ô�Į�ı䴰�ڴ�С������ģ��)
			dm.SetWindowState(GB.getHwnd(), 8);
			dm.UnBindWindow();
			dm.MoveTo(x+width-5, y+height-5);
			dm.LeftClick();
			//���°�
			if(dm.BindWindow(GB.getHwnd(), "gdi", "windows", "windows", 0)==0){
				System.out.println("�󶨾��ʧ�ܣ����飡");
				return;
			}
		}
	}//GameWindowAdjust
	

	/**
	 * ��dm���󶨵���Ϸ��ǰ������Ѱ��ָ��ͼƬ
	 * @param dm ָ�����˾���Ĵ�Į����
	 * @param seekPicPath ����ͼƬ��·��
	 * @return  ����ImgSeekBean����
	 */
	public static ImgSeekBean GameSeek(Dm2Java dm,String seekPicPath){
		
		String gamePicPath = "./res/pics/pic_temp/game.jpg";
		dm.DeleteFile(gamePicPath);
		dm.Capture(GB.getGx(),GB.getGy(), GB.getWidth(), GB.getHeight(), gamePicPath);
		 ImgSeekBean isb = new ImgSeekBean();
	        isb = ImgSeek.findImg(seekPicPath, gamePicPath,false);
	        if(isb.isSeek()&&isb.getX1()!=-1){
	        	int x = isb.getX1()+(isb.getX2()-isb.getX1())/2,
	        			y = isb.getY1()+(isb.getY2()-isb.getY1())/2;
	        	isb.setMidX(x);
	        	isb.setMidY(y);
//	        	System.out.println("ģ��ͼ��ԭͼƥ��ɹ���");
//	        	System.out.println("ƥ�����Ͻ�λ��(x1="+isb.getX1()+",y1="+isb.getY1()+")");
//	        	System.out.println("ƥ�����½�λ��(x2="+isb.getX2()+",y2="+isb.getY2()+")");
	        	
	        }
	        else{
	        	isb.setSeek(false);
//	        	System.out.println("ģ��ͼ��ԭͼƥ��ʧ��.");
	        }
//	        
//	        System.out.println("ģ��ͼ������������" + isb.getTempKeyPointsNum());
//	        System.out.println("ƥ���������������" + isb.getMatchKeyPointsNum());
	
		return isb;
		
	}
	
	/**
	 * ��Ե���������͵��쳣�������Ļ����
	 */
	public static void GameClear(Dm2Java dm){
		System.out.println("--��Ļ����");
		ImgSeekBean isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Game/Bt_Act.jpg");
		if(isb.isSeek())
			System.out.println("��Ļ�ɾ���");
		else
			System.out.println("�����ڵ���");
			
			
	}
	
	
	
	
}	
