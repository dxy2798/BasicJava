package com.atguigu.javase.lesson13;

public class InterruptThreadTest extends Thread {

	public static void main(String[] args) {

		InterruptThreadTest itt = 
				new InterruptThreadTest();
		itt.start();
		
		itt.interrupt();
	}
	
	@Override
	public void run() {
		for(int i = 0;i < 100;i++){
			System.out.println(getName() + ": "
					+ i);
			
			if(i == 10){
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
