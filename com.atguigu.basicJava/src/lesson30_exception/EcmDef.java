package lesson30_exception;

import java.util.Scanner;

public class EcmDef {
	
	public static int ecm(int i,int j){
		
		if(i < 0 || j < 0){
			throw new EcDef("不能输入负数.");
		}
		
		return i / j;
	}

	public static void main(String[] args) {
		
		try{
			int i = Integer.parseInt(args[0]);
			int j = Integer.parseInt(args[1]);
			int result = ecm(i, j);
			System.out.println("result: " + result);
		}catch (NumberFormatException e){
			System.out.println("输入的参数不能转为 int 类型.");
		}catch (ArrayIndexOutOfBoundsException e){
			System.out.println("输入的参数个数不足.");		
		}catch (ArithmeticException e){
			System.out.println("除数不能为 0.");
		}catch (EcDef e){
			//System.out.println("运算的数不能为负数.");
			System.out.println(e.getMessage());
		}
		

		
		
		
	}


}
