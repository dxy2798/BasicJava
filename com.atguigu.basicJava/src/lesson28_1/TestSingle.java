package lesson28_1;

public class TestSingle {

	public static void main(String[] args) {

//		Single single1 = new Single();
//		Single single2 = new Single();
		
		Single instance1 = Single.getInstance();
		Single instance2 = Single.getInstance();
		
		System.out.println(instance1 == instance2);
	}

}
