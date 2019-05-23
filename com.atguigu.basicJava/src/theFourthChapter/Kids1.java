package theFourthChapter;

public class Kids1 extends ManKind{

		int yearsOld;
		
		public void printAge(){
			System.out.println(this.yearsOld);
		}
		
		public void employeed(){
			super.employeed();
			System.out.println("but Kids should study and no job");
		}
		public static void main(String[] args) {
			
			Kids1 someKid = new Kids1();
			someKid.sex = 1;
			someKid.yearsOld = 10;
			someKid.salary = 1000;
			
			someKid.manOrWoman();
			someKid.employeed();
			someKid.printAge();
			
			
		}
}
