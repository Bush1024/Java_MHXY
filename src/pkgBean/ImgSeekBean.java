package pkgBean;
/**
 * �洢ͼ��ƥ�����������Ϣ
 * 
*/
public class ImgSeekBean {
	/** �Ƿ�ƥ��ɹ�; */
	private boolean Seek;
	/**
	 *ƥ��ɹ���ģ��ͼ��ԭͼ�еĵ����Ͻǡ����½����� ,��ʼΪ(-1,-1)(-1,-1)
	 */
	private int x1=-1,x2=-1,y1=-1,y2=-1;
	
	/**
	 * ģ��ͼ������������
	 */
	private int tempKeyPointsNum=-1;
	
	/**
	 * ģ��ͼ��ԭͼ��ƥ�������������
	 */
	private int matchKeyPointsNum=-1;
	
	/**
	 * ƥ��ɹ���ģ��ͼ���ĵ���������
	 */
	private int midX,midY;
	public boolean isSeek() {
		return Seek;
	}
	public void setSeek(boolean seek) {
		Seek = seek;
	}
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public int getTempKeyPointsNum() {
		return tempKeyPointsNum;
	}
	public void setTempKeyPointsNum(int tempKeyPointsNum) {
		this.tempKeyPointsNum = tempKeyPointsNum;
	}
	public int getMatchKeyPointsNum() {
		return matchKeyPointsNum;
	}
	public void setMatchKeyPointsNum(int matchKeyPointsNum) {
		this.matchKeyPointsNum = matchKeyPointsNum;
	}
	public int getMidY() {
		return midY;
	}
	public void setMidY(int midY) {
		this.midY = midY;
	}
	public int getMidX() {
		return midX;
	}
	public void setMidX(int midX) {
		this.midX = midX;
	}
	
}
