package com.atguigu.javase.lesson13;

public class PrintNumber2 {
	
	public static void main(String[] args){
		int i = 0;
		NumberThread thread1 = new NumberThread("线程-1");
		NumberThread thread2 = new NumberThread("线程-2");
		
		thread1.setI(i);
		
		thread1.start();
		thread2.start();
		
	}
	
}

class NumberThread extends Thread{
	
	public NumberThread(String threadName) {
		super(threadName);
	}
	
	private static int i;
	
	public static void setI(int i) {
		NumberThread.i = i;
	}
	
	@Override
	public void run() {
		String threadName = 
				NumberThread.currentThread().getName();
		for(i = 0;i < 100;i++){
			System.out.println(threadName + ": " + i);
		}
	}
}
