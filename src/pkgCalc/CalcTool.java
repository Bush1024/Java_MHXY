package pkgCalc;

import java.awt.Color;

/**
 * ��ֵ���㹤����
 */
public class CalcTool {
	/**
	 * ����Įȡɫ����ֵ���ַ���"RRGGBB"ת��ΪColor��ɫ����
	 * @param dmC	��Į��ɫֵ���ַ���
	 * @return	����Color����
	 */
	public static Color dmCToC(String dmC){
		Color c =null;
		try{
		c = new Color(Integer.parseInt(dmC.substring(0, 2),16),
							Integer.parseInt(dmC.substring(2, 4),16),
							Integer.parseInt(dmC.substring(4, 6),16));
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}
		return c;
	}
}
