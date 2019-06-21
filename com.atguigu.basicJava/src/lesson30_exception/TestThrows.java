package lesson30_exception;

public class TestThrows {
	
	public static void main(String[] args){
		
		try {
			test();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void test() throws ClassNotFoundException{
		Class.forName("com.atguigu.javase.Abc");
	}

}
