package com.atguigu.javase.lesson13;
/**
 * 关于线程:
 * 1. 在 Java 中, Thread 类代表一个线程.
 * 
 * 2. 实现线程有 2 种方式:
 * 2.1 继承 Thread 类.
 * 2.2 实现 Runnable 接口.
 *
 * 3. 继承 Thread 类:
 * 3.1 必须重写 run() 方法: 里边放置的是实际的线程体.
 * 
 * 4. 启动线程:
 * 4.1 创建 Thread 对象.
 * 4.2 调用 Thread 对象的 start() 方法启动线程. 而不是 run() 方法.
 * 
 */
public class ThreadTest {

	public static void main(String[] args) {
		
		// 1. 创建线程对象
		Thread thread = new FirstThread("FirstThread");
		
		// 2. 调用线程对象的 start() 方法启动线程.
		thread.start();
		
		String threadName = 
				Thread.currentThread().getName();
		for(int i = 0;i < 100;i++){
			
			System.out.println(threadName + ":" + i);
		}
		
	}
	
}

	class FirstThread extends Thread{
		
		public FirstThread(String name) {
			super(name);
		}
		
		
		/**
		 * 线程体在 run() 方法中
		 */
		
		@Override
		public void run() {
			String threadName = 
					Thread.currentThread().getName();
			for(int i = 0;i < 100;i++){
				
				System.out.println(threadName + ":" + i);
			}
		}
	}
