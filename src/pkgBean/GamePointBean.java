package pkgBean;

import java.awt.Color;

/**
 * ��Ϸ���࣬��¼��Ϸ��ĳ���������
 */
public class GamePointBean{
	/**
	 * ��xy���꣬�������Ϸ����
	 */
	public int x,y;
	/**
	 * ��׼��ɫֵ����Color����洢
	 */
	public Color c;
	/**
	 * ��Į��ɫֵ,RRGGBB���ַ�����ʽ�洢
	 */
	public String dmC;
	@Override
	public String toString() {
		return "GamePointBean [(" + x + "," + y + "), c=" + c + "]";
	}
}

