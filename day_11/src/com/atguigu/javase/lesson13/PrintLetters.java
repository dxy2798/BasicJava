package com.atguigu.javase.lesson13;

public class PrintLetters implements Runnable{

	private char c = 'a';
	
	public synchronized boolean print(){
		//synchronized(this){
			if(c <= 'z'){
				System.out.println(Thread.currentThread().getName() + ": " + c);
				c++;
				return true;
			}
			
			return false;
		}
		
	//}
	
	
	public static void main(String[] args) {

		PrintLetters printLetters = new PrintLetters();
		
		Thread tr1 = new Thread(printLetters, "线程-1");
		Thread tr2 = new Thread(printLetters, "线程-2");
		
		tr1.start();
		tr2.start();
	}




	@Override
	public void run() {
		boolean flag = print();
		while(flag){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flag = print();
		}
		
	}

}
