package pkgTest;


import com.jacob.com.Variant;

import pkgDm.Dm2Java;

public class TestFindStr {
	public static void main(String args[]){
		Dm2Java.DMInit();
		
		Dm2Java dm = new Dm2Java();
	
		
		//�����ֿ�·��
		dm.SetDict(0, "./res/WordsLib/words00.txt");		
		System.out.println("�ַ����سɹ���������"+dm.GetDictCount(0));
		//����ָ���ַ�
		Variant x = new Variant(-1,true);
		Variant y = new Variant(-1,true);
		int v = dm.FindStrFast(0, 0, 1920, 1080, "��", "654b36-050505|553923-101010", 0.75, x, y);
		if(v==0)
			System.out.println("�ַ����ҳɹ�����x="+x+",y="+y);
		else
			System.out.println("�ַ�����ʧ�ܣ���x="+x+",y="+y);
			
		
		
		Dm2Java.DMClear();
	}
}