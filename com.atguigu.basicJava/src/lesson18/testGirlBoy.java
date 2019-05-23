package lesson18;

public class testGirlBoy {

	public static void main(String[] args) {
		
		Boy boy = new Boy("Tom");
		
		GIRL girl = new GIRL("Joan");
		
		boy.marry(girl);

		System.out.println(girl);
		
		System.out.println(girl.shengcheng());
	}

	
	
}
