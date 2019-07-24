package com.atguigu.javase.lesson13;



public class ShareApple implements Runnable{
	
	private int appleCount = 15;

	
	// 拿苹果.
	boolean getApple(){
		
		synchronized(this){
			if(appleCount > 0){
				appleCount--;
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() 
						+ "拿走了一个苹果.还剩下 " + appleCount + " 个苹果.");
				return true;
			   }
			return false;
			}
		}
		

	
	// 线程体
	@Override
	public void run() {
		boolean flag = getApple();
		while(flag){
			flag = getApple();
		}
		System.out.println(Thread.currentThread().getName() + "线程结束了");
	}
	
	public static void main(String[] args) {

		ShareApple shareApple = new ShareApple();
		
		Thread th1 = new Thread(shareApple);
		Thread th2 = new Thread(shareApple);
		
		th1.setName("小明");
		th2.setName("小强");
		
		th1.start();
		th2.start();
	}

	

}
