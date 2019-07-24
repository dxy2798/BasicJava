package com.atguigu.javase.lesson13;

public class PrintNumber {
	
	int i = 0;
	
	public static void main(String[] args){
		
		PrintNumber printNumber = new PrintNumber();
		
		NumberThread thread1 = new NumberThread("线程-1",printNumber);
		NumberThread thread2 = new NumberThread("线程-2",printNumber);
			
		thread1.start();
		thread2.start();
		
	}
	
}

class NumberThread extends Thread{
	
	PrintNumber printNumber;
	
	public NumberThread(String threadName,PrintNumber printNumber) {
		super(threadName);
		this.printNumber = printNumber;
	}
	
	@Override
	public void run() {
		String threadName = 
				NumberThread.currentThread().getName();
		for(;printNumber.i < 100;printNumber.i++){
			System.out.println(threadName + ": " + printNumber.i);
		}
	}
}
