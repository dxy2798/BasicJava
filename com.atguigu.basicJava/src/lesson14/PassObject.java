package lesson14;

public class PassObject {

	private void printAreas(Circle c,int time){
		System.out.println("Radius\t\t\tArea");
		for(int i = 1; i <= time;i++){
			c.setRadius(i);
			System.out.println(c.getRadius() + "\t\t\t" + c.findArea());
		}
		
	}
	public static void main(String[] args) {
		new PassObject().printAreas(new Circle(), 5);
	}

}
