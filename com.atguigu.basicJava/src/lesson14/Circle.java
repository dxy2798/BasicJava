package lesson14;

public class Circle {
	private double radius;

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double findArea(){
		double area = Math.PI * radius * radius;
		return area;
	}
}
