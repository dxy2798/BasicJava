package lesson30_exception;

public class TestFinally {
	
	public static void main(String[] args){
		
		int result = test();
		System.out.println(result);
	}
	
	public static int test(){
		try{
			int i = 10 / 0;
		}catch(ArithmeticException e){
			e.printStackTrace();
			return 10;
		}
			return 0;
		
		
		
		
	}
}
