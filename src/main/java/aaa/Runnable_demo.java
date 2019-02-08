package aaa;

public class Runnable_demo implements Runnable{
	private int ticket = 10;

	public Runnable_demo() {
	}

	@Override
	public void run() {
		for (int i = 0; i <20; i++) {
			System.out.println("第"+i+"次");
			if (this.ticket > 0) {
				// 休眠1s秒中，为了使效果更明显，否则可能出不了效果
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				this.sale();
			}

		}
		
	}

	   
    public synchronized void sale(){  
        if(this.ticket>0){  
        	System.out.println(Thread.currentThread().getName() + "号窗口卖出：" + this.ticket-- + "号票\t\t还剩"+ticket+"张票");  
        }  
    }  
	
	public static void main(String args[]) {
		Runnable_demo demo = new Runnable_demo();
		
		System.out.println("开始售票");
		// 基于火车票创建三个窗口
		new Thread(demo, "A").start();
		new Thread(demo, "B").start();
		new Thread(demo, "C").start();
		
	}
}
