package theFourthChapter;

public class testCircle {

	public static void main(String[] args) {
		
		Circle circle = new Circle(2);
		
		
		Cylinder cylinder = new Cylinder(2,3);
//		cylinder.setLength(3);
//		cylinder.setRadius(2);
		System.out.println(cylinder.findVolume());

	}

}
