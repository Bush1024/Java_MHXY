package pkgTest;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class TestDM {
	public static void main(String args[]){
	//ComThread.InitSTA();//����ǰJava�̳߳�ʼ��ΪSTA���̵߳�Ԫ(Ӱ��Java-dll����ͨ��)
		
		//����ActiveXComponentʵ����һ����Į�������
		ActiveXComponent dm = new ActiveXComponent("dm.dmsoft");
		//��������&���ö���Dispatch��ʵ��
		Dispatch dmCom = (Dispatch)dm.getObject();
		//ͨ��Dispatch���ô�Įdll�е�Ver����������Variant���ܷ��ؽ�����˴�Ϊ�汾��
		Variant variant = Dispatch.call(dmCom, "Ver");
		System.out.println(variant.toString());
		
		ComThread.Release();//��Com���ͷ�Java�߳���Դ
	}
}
