package lesson18;

public class Boy {

	private String name;

	public Boy(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void marry(GIRL girl){
		
		System.out.println("嫁给我吧: " + girl.getName());
		
		girl.shout(this);
	}

}
