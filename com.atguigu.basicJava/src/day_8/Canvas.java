package day_8;

import java.util.List;

public class Canvas {

	public void draw(Shape s){
		s.draw(this);
	}
	
	// Shape 是这个通配符的上限
	public void drawAll(List<? extends Shape> shapes){
		for(Shape s: shapes){
			s.draw(this);
		}
	}
	
	public void addRectangle(List<? extends Shape> shapes){
		
		shapes.addAll(0, new Rectangle());
	}
	
}
