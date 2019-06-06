package lesson24_5;

public class TestCircle {

	public static void main(String[] args) {

		Circle c1 = new Circle(2.0,"blue",11.0);
		Circle c2 = new Circle(2.0,"red",10.0);
		
		//判断颜色是否相等
		System.out.println(c1.getColor() == c2.getColor());
		//面积
		System.out.println("c1的面积为: " + c1.finaArea());
		System.out.println(c1.equals(c2));
		System.out.println(c1.toString());		
		
	}

}
