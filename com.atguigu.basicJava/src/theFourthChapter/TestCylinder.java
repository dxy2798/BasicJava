package theFourthChapter;

public class TestCylinder {

	public static void main(String[] args) {
		
		Cylinder cylinder = new Cylinder(2,3);
		
		cylinder.setRadius(2);
		cylinder.setLength(3);
		System.out.println(cylinder.findVolume());
		System.out.println(cylinder.findArea());
		Circle circle = new Circle(2);
		circle.setRadius(2);
		System.out.println(circle.findArea());
		
	}

}
