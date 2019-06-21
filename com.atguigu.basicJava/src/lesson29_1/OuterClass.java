package lesson29_1;

public class OuterClass {

	private String outerName = "ABC";
	
	
	static class StaticInnerClass{
		private String staticInnerName = "HIJ";
		public void test(){
			// 静态内部类中的方法不能访问外部类的非静态成员
			//System.out.println("outerName: " + outerName);
			System.out.println("staticInnerName: " + staticInnerName);			
		}
		
	}
	
	private int i = 1;
	
	class InnerClass{
		private String innerName = "def";
		
		private int i = 2;
		
		public void test(){
			int i = 3;
			
			System.out.println(i);
			System.out.println(this.i);
			System.out.println(OuterClass.this.i);
			
			System.out.println("outerName: " + outerName);
			System.out.println("innerName: " + innerName);			
		}
	}
	
}
