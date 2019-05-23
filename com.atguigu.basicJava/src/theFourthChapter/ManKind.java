package theFourthChapter;

public class ManKind {

	int sex;
	int salary;
	
	public void manOrWoman(){
		if(this.sex == 1){
			System.out.println("man");
		}else if(this.sex == 0){
			System.out.println("women");
		}
	}
	
	public void employeed(){
		if(this.salary == 0){
			System.out.println("no job");
		}else{
			System.out.println("job");
		}
	}
	
	
}
