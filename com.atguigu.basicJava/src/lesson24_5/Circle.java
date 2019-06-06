package lesson24_5;

public class Circle extends GeometricObject {

	private double radius;
	
	public Circle(){
		this.color = "white";
		this.weight = 1.0;
		this.radius = 1.0;
	}
	public Circle(double radius) {
		
		this();
		this.radius = radius;
	}
	
	public Circle(double radius,String color, double weight) {
		this.color = color;
		this.weight = weight;
		this.radius = radius;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		
		if(this.radius == other.radius){
			return true;
		}
		
		return true;
	}
	@Override
	public String toString() {
		return radius + "";
	}

	public double finaArea(){
		return Math.PI * radius * radius;
	}
	
	
}
