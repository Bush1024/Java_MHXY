package pkgBean;

import java.util.Date;

import pkgAssist.MhxyAssist;

/**
 *	��¼��Ϸȫ�����ݡ�״̬��Ϣ 
 * @author bush
 */
public class GameBean {
//��Ϸ״̬���==================================================
	/**
	 * ��Ϸ���
	 */
	private int hwnd;
	

	/**
	 * ��Ϸ��ǰ״̬�������ַ�����ʾ�ɣ������Ż�ʱ���������ִ��룻
	 * ...
	 */
	private String state;
	/**
	 * ��ǰ״̬����ʱ��
	 */
	private int stateTime = 0;
	
	/**
	 * ��ǰ����ִ�еĽű�����
	 */
	private String jobDoing;
	
	/**
	 * ��Ϸ������
	 */
	private int width,height;
	
	/**
	 * ��Ϸ���Ͻ��������Ļ���� 
	 */
	private int gx,gy;
	
	/**
	 * ��Ϸ״̬����,�����ж���Ϸ״̬
	 */
	private GamePointBean gps[] = null;
	
	/**
	 * ս��״̬����
	 */
	private GamePointBean fightPoint=null;
	
	/**
	 * �ű���ʼʱ�������ʱ��
	 */
	private Date assistStart = null,assistEnd = null;
//ץ���������==================================================
	/**
	 * ��ǰ�ڼ�ֻ��
	 */
	private int nowGuiNum=0;
	
	/**
	 * �Ƿ���Ҫ���õ�ͼ��ת�ߵ���ظ��
	 */
	private boolean mapGo = true;
	
	
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		GameBean GB = MhxyAssist.GB;
		if(state.equals("ս��")&&GB.getJobDoing().equals("ץ������"))
			GB.setNowGuiNum(GB.getNowGuiNum()+1);
		this.state = state;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getGx() {
		return gx;
	}

	public void setGx(int gx) {
		this.gx = gx;
	}

	public int getGy() {
		return gy;
	}

	public void setGy(int gy) {
		this.gy = gy;
	}


	public Date getAssistEnd() {
		return assistEnd;
	}

	public void setAssistEnd(Date assistEnd) {
		this.assistEnd = assistEnd;
	}

	public Date getAssistStart() {
		return assistStart;
	}

	public void setAssistStart(Date assistStart) {
		this.assistStart = assistStart;
	}

	public int getHwnd() {
		return hwnd;
	}

	public void setHwnd(int hwnd) {
		this.hwnd = hwnd;
	}

	public int getStateTime() {
		return stateTime;
	}

	public void setStateTime(int stateTime) {
		this.stateTime = stateTime;
	}

	public GamePointBean[] getGps() {
		return gps;
	}

	public void setGps(GamePointBean[] gps) {
		this.gps = gps;
	}

	public String getJobDoing() {
		return jobDoing;
	}

	public void setJobDoing(String jobDoing) {
		this.jobDoing = jobDoing;
	}

	public GamePointBean getFightPoint() {
		return fightPoint;
	}

	public void setFightPoint(GamePointBean fightPoint) {
		this.fightPoint = fightPoint;
	}

	public int getNowGuiNum() {
		return nowGuiNum;
	}

	public boolean isMapGo() {
		return mapGo;
	}

	public void setNowGuiNum(int nowGuiNum) {
		this.nowGuiNum = nowGuiNum;
	}

	public void setMapGo(boolean mapGo) {
		this.mapGo = mapGo;
	}
	

}//GameBean


