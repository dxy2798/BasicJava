package com.atguigu.javase.lesson13;

public class TicketHouse implements Runnable{

	private int fiveCount = 1,tenCount = 0, twentCount = 0;
	
	public synchronized void buy(){
		
		String name = Thread.currentThread().getName();
		
		//zf: 20 元
		if("zf".equals(name)){
			if(fiveCount < 3){
				
				try {
					System.out.println("5 元面值: " + fiveCount + ", 张飞必须等待.");
					wait();
					System.out.println("5 元面值: " + fiveCount + ", 卖一张票给张飞.找零15.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else if("gy".equals(name)){
			fiveCount++;
			System.out.println("卖一张票给关羽.钱正好. 5 元面值: " + fiveCount);
			
		}else if("lb".equals(name)){
			fiveCount++;
			System.out.println("卖一张票给刘备.钱正好. 5 元面值: " + fiveCount);
		
		}
		if(fiveCount == 3){
			notifyAll();
		}
		
		
	}
	
	public static void main(String[] args) {

		Runnable runnable = new TicketHouse();
		
		Thread th1 = new Thread(runnable);
		th1.setName("zf");
		
		Thread th2 = new Thread(runnable);
		th2.setName("gy");
		
		Thread th3 = new Thread(runnable);
		th3.setName("lb");
		
		th1.start();
		th2.start();
		th3.start();
		
	}

	@Override
	public void run() {
		
		buy();
	}

}
