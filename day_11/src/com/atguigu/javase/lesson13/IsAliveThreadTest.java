package com.atguigu.javase.lesson13;

public class IsAliveThreadTest extends Thread {

	public static void main(String[] args){
		Thread thread = new IsAliveThreadTest();
		System.out.println(thread.isAlive());
		thread.start();
		System.out.println(thread.isAlive());
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(thread.isAlive());
		
		// 已经结束的线程, 再调用 start() 方法会抛出异常.
		// thread.start();
	}
	
	@Override
	public void run() {
		for(int i = 0;i < 100;i++){
			System.out.println(i);
		}
	}
	
}
