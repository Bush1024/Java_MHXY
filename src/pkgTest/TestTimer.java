package pkgTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ����Java��Timer��ʱ����TimerTask����
 */
public class TestTimer {
	//ÿ��Timer�������һ����̨�̣߳����ڰ�˳��ִ�����м�ʱ��������
	static int i=0;
	public static void main(String args[]) throws AWTException{
		Timer myTimer = new Timer();
		TimerTask myTimerTask1 = new TimerTask(){
			
			public void run() {
				System.out.println(i++);
//				if(this.i==5)
//					//��ʱ���������Ҫͨ��Timer��cancel()��ȡ������̣߳���������һֱ����
//					myTimer.cancel();
				}
		};
		myTimer.schedule(myTimerTask1, 2000,1000);
		
		Robot robot = new Robot();
		while(i<=10){
			robot.delay(1000);
			if(i==5){
				myTimer.cancel();
				System.out.println("Timer��Robotȡ���ˣ�");
				i++;
			}
			else if(i>5){
				System.out.println("������Robot���ӹ�i++��i="+i++);
			}
		}
		
			
		
	}
}