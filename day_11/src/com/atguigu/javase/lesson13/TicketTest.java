package com.atguigu.javase.lesson13;

/**
 * 练习:
 * 1. 编写出一个线程安全的实例代码.
 * 2. 使用同步代码块解决线程安全的问题.
 */


public class TicketTest implements Runnable{

	int ticketCount = 1000;
	
	boolean getTicket(){
		
		synchronized(this){
			if(ticketCount > 0){
				
				ticketCount--;
								
				System.out.println(Thread.currentThread().getName() 
						+ "卖出 1 张.还剩下 " + ticketCount + " 张.");
				return true;
			}
			return false;
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		TicketTest ticketTest = new TicketTest();
		
		Thread[] threads = new Thread[100];
		
		for(int i = 0;i < 100;i++){
			threads[i] = new Thread(ticketTest);
			threads[i].start();
		}
		
	/*		Thread tr0 = new Thread(ticketTest);
		    tr0.setName("窗口0");
		    
			Thread tr1 = new Thread(ticketTest);
		    tr1.setName("窗口1");
		    
		    Thread tr2 = new Thread(ticketTest);
		    tr1.setName("窗口3");
			
		    
		    tr0.start();
		    tr1.start();
		    tr2.start();*/
		
	}

	public void run() {
		boolean flag = getTicket();
		while(flag){
			flag = getTicket();
		}
		System.out.println(Thread.currentThread().getName() + "线程结束了");
	}

}
