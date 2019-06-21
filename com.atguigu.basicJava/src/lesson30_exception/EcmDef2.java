package lesson30_exception;

import java.util.Scanner;

public class EcmDef2 {

	public static void main(String[] args) {
/*		System.out.println("请输入被除数: ");
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		System.out.println("请输入除数: ");
		in = new Scanner(System.in);
		int b = in.nextInt();		
		
		System.out.println(a + "除以" + b + "的结果为: " + ecm(a,b));*/
		
		
		
		
	}

	public static double ecm(int a,int b){
		
		if(b == 0){
			throw new ArithmeticException();
		}else if(a < 0 || b < 0){
			throw new EcDef("除数和被除数均不得为负数");
		}else{
			return a / b;
		}
		
	}
}
