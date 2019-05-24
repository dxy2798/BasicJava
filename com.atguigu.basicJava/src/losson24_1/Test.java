package losson24_1;

public class Test {

	public boolean equalsArea(GeometricObject x,GeometricObject y){
		
		double a = x.findArea();
		double b = y.findArea();
		
		return a == b;
	}
	
	public void displayGeometricObject(GeometricObject o){
		System.out.println("area: " + o.findArea());
	}
	
	public static void main(String[] args) {

		GeometricObject circle = new Circle(10, "red", 10);
		
		circle.findArea();
		GeometricObject myRectangle = new MyRectangle(5, 10, "blue", 15);
		
		Test test = new Test();
		
		System.out.println(test.equalsArea(circle, myRectangle));

		test.displayGeometricObject(circle);
		test.displayGeometricObject(myRectangle);
	}

}
