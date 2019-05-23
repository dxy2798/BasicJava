package lesson18;

public class PassRef {

	int x;
	public static void main(String[] args) {
		PassRef obj = new PassRef();
		
		obj.x = 5;
		
		change(obj);
		
		System.out.println(obj.x);
	}
	private static void change(PassRef obj) {
		obj.x = 3;
	}

}
