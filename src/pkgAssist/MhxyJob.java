package pkgAssist;

import java.awt.event.KeyEvent;

import pkgBean.ImgSeekBean;
import pkgDm.Dm2Java;

/**
 * ��Ϸ�ű������࣬�����Ϸ��������Ϸ��ʵ�ʵ����񡢻�Ƚű��߼�
 *
 */
public class MhxyJob {
	/**
	 * ��ͼ����-���ܱ�ͼ������ɻ�ɱ
	 */
	public static void Job_BaoTu(Dm2Java dm){
		
	//��Ļ���->����
	MhxyAssist.GameClear(dm);
	
	//-��ʼ��
	System.out.println("--��ʼִ�н��ܱ�ͼ������ɻ�ɱ:");
	MhxyAssist.GB.setJobDoing("��ͼ����");
	ImgSeekBean isb = new ImgSeekBean();
	isb.setSeek(false);
	
	
	//����Ƿ���ڳ�����
	isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Game/City_CAC.jpg");
	if(!isb.isSeek()){//���������ͼ��ת������ֱ�ӿ�С��ͼ
		
		isb.setSeek(false);//����
		//-������С��ͼ-�ȴ���ͼ�л���ť����
		dm.KeyPress(KeyEvent.VK_TAB);
		while(!isb.isSeek()){
		dm.delay(1000);
		isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_BaoTu/Bt_MapChange.jpg");
		}
		isb.setSeek(false);//����
		
		//-����л����ͼ-�ȴ������Ǵ��ֳ���
		dm.ClickXY(isb.getMidX(), isb.getMidY());
		while(!isb.isSeek()){
			dm.delay(1000);
			isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Game/Words_CAC.jpg");
		}
		isb.setSeek(false);//����
		
		//-���ҵ��������-�ȴ����Ͻǳ�����ͼ�����
		dm.ClickXY(isb.getMidX(), isb.getMidY());
		while(!isb.isSeek()){
			dm.delay(1000);
			isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Game/City_CAC.jpg");
		}
		isb.setSeek(false);//����
	}
	isb.setSeek(false);
	//-������С��ͼ-�ȴ���С������
	dm.KeyPress(KeyEvent.VK_TAB);
	while(!isb.isSeek()){
	dm.delay(1000);
	isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_BaoTu/Name_DXE.jpg");
	}
	isb.setSeek(false);//����
	
	//-���ҵ����С��-�ȴ�"�����޷�"
	dm.ClickXY(isb.getMidX(), isb.getMidY());
	dm.delay(3000);//Ԥ���ߵ���С��λ�õ�ʱ��
	while(!isb.isSeek()){
	dm.delay(3000);
	isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_BaoTu/Words_TTWF.jpg");
	}
	isb.setSeek(false);//����
	
	//-���"�����޷�"-����ESC�رնԻ�
	dm.ClickXY(isb.getMidX(), isb.getMidY());
	dm.delay(500);
	dm.KeyPress(KeyEvent.VK_ESCAPE);
	
	//-���ҵ����ɱǿ������
	while(!isb.isSeek()){
		dm.delay(1000);
		isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_BaoTu/Words_BTRW.jpg");
	}
	dm.ClickXY(isb.getMidX(), isb.getMidY());
	
	}//Job_BaoTu
	
	/**
	 *	����ץ�����񲢵����ʼ��һ��ս��
	 */
	public static void Job_Gui(Dm2Java dm){
		//-��ʼ��
		System.out.println("--��ʼ����ץ������:");
		MhxyAssist.GB.setJobDoing("ץ������");
		MhxyAssist.GB.setNowGuiNum(0);
		ImgSeekBean isb = new ImgSeekBean();
		isb.setSeek(false);
		//�ж��Ƿ���Ҫ���õ�ͼ�ߵ���ظ��
		if(MhxyAssist.GB.isMapGo()){
			
			//�ж��Ƿ���ڳ�����
			if(!MhxyAssist.GameSeek(dm, "./res/pics/pic_Game/City_CAC.jpg").isSeek()){
				
				//-������С��ͼ-�ȴ���ͼ�л���ť����
				dm.KeyPress(KeyEvent.VK_TAB);
				while(!isb.isSeek()){
				dm.delay(1000);
				isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_BaoTu/Bt_MapChange.jpg");
				}
				isb.setSeek(false);//����
				
				//-����л����ͼ-�ȴ������Ǵ��ֳ���
				dm.ClickXY(isb.getMidX(), isb.getMidY());
				while(!isb.isSeek()){
					dm.delay(1000);
					isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Game/Words_CAC.jpg");
				}
				isb.setSeek(false);//����
				
				//-���ҵ��������-�ȴ����Ͻǳ�������������
				dm.ClickXY(isb.getMidX(), isb.getMidY());
				while(!isb.isSeek()){
					dm.delay(1000);
					isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Game/City_CAC.jpg");
				}
				isb.setSeek(false);//����
			}//�Ƿ��ڳ�����
			
			//-������С��ͼ-�ȴ���ظ����
			dm.KeyPress(KeyEvent.VK_TAB);
			while(!isb.isSeek()){
			dm.delay(1000);
			isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Gui/Name_ZK.jpg");
			}
			isb.setSeek(false);//����
		}//�Ƿ���Ҫ���õ�ͼ
		
		//-�����ظ-�ȴ�ץ���������
		dm.ClickXY(isb.getMidX(), isb.getMidY());
		dm.delay(5000);//Ԥ���ߵ���ظ��ʱ��
		while(!isb.isSeek()){
			dm.delay(1000);
			isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Gui/Words_ZGRW.jpg");
		}
		isb.setSeek(false);//����
		
		//-���"ץ������"-����ESC�رնԻ�
		dm.ClickXY(isb.getMidX(), isb.getMidY());
		dm.delay(500);
		
		//�ж��Ƿ񵯴�"ȱ��"
		isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Gui/Words_Yes.jpg");
		if(isb.isSeek())
			dm.ClickXY(isb.getMidX(), isb.getMidY());
		dm.delay(500);
		//�رնԻ�
		dm.KeyPress(KeyEvent.VK_ESCAPE);
		
		//-���ҵ��"ץ��"����ʼ��һ��ս��
		while(!isb.isSeek()){
			dm.delay(1000);
			isb = MhxyAssist.GameSeek(dm, "./res/pics/pic_Gui/Words_ZG.jpg");
		}
		isb.setSeek(false);
		dm.ClickXY(isb.getMidX(), isb.getMidY());
		
	}//Job_Gui
}
