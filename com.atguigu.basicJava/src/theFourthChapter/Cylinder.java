package theFourthChapter;

public class Cylinder extends Circle{

	double length;

	
	public Cylinder(double radius, double length) {
		super(radius);
		this.length = length;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	
	public double findVolume(){
		return super.findArea() * length;
	}
	
	public double findArea(){
		return super.findArea() * 2 + Math.PI * 2 * this.getRadius() * this.getLength();
	}
	
}
