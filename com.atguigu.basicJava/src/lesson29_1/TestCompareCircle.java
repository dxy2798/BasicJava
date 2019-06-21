package lesson29_1;

public class TestCompareCircle {


	public static void main(String[] args) {
		CompareCircle c1 = new CompareCircle();
		c1.setRadius(3);
		
		CompareCircle c2 = new CompareCircle();	
		c2.setRadius(2);
		
		
		System.out.println(c1.compareTo(c2));
	}

}
