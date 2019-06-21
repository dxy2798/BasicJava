package lesson30_exception;

public class TestException {

	public static void main(String[] args) {
		
		//1. 数组下标越界异常
		//   java.lang.ArrayIndexOutOfBoundsException
		//int[] scores = new int[10];
		//scores[10] = 100;
		
		//2. 空指针异常
		//   java.lang.NullPointerException
		//int [][] yh = new int[10][];
		//yh[0][0] = 10;
		
		//3. 数学异常:
		//   java.lang.ArithmeticException 
		//int i = 10/0;
		
		//4. 类型转换异常
		//   java.lang.ClassCastException
		//Object obj = new TestException();
		//Person person = (Person)obj;
		
		//Error 示例:
		//main(new String()[]{});
		
		try {
			Class.forName("lesson30_exception.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
