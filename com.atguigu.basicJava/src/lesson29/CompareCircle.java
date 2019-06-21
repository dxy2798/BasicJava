package lesson29;

public class CompareCircle extends Circle implements CompareObject {



	public CompareCircle(double radius) {
		this.radius = radius;
	}

	public CompareCircle() {
	}

	@Override
	public int compareTo(Object o) {
		
		if(o instanceof Circle){
			Circle c = (Circle) o;
			return (int)(this.radius - c.radius);
		}
		
		return 0;
	}

}
