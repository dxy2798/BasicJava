package lesson29;

public abstract class Circle {

	protected double radius;

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Circle(double radius) {
		super();
		this.radius = radius;
	}
	
	public Circle(){
		
	}
	
	public double findArea(){
		return Math.PI * this.radius * this.radius;
	}
	
	
}
