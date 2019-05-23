package lesson18;

public class GIRL {
	
	private String name;
	
	

	public GIRL(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void shout(Boy boy){
		System.out.println("我愿意: " + boy.getName());
	}
	
	public GIRL shengcheng(){
		return this;
	}
	

	
}
