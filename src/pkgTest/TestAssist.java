package pkgTest;

import pkgAssist.MhxyAssist;
import pkgAssist.MhxyJob;
import pkgDm.Dm2Java;

public class TestAssist {
	public static void main(String args[]){
		//����Java���ô�Į����
		Dm2Java dm = new Dm2Java();
		//�������߳�ʼ����������Ϸ���̾�����ɴ�Į���߻�ȡ
		MhxyAssist.AssistInit(328552);
		//����Ϸȫ��״̬���
		MhxyAssist.GameMonitor(dm);
		
		//ִ��ָ���ű�����
//		MhxyJob.Job_BaoTu(dm);
		MhxyJob.Job_Gui(dm);
		MhxyAssist.GB.setNowGuiNum(4);
		
	}//main	
}
