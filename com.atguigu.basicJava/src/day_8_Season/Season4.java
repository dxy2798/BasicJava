package day_8_Season;

public enum Season4 implements Info{

	//枚举类的实例需要在第一行列出.
	SPRING("春天", "春风又绿江南岸"){
		@Override
		public String getInfo() {
			
			return "^^A^^";
		}
	},
	SUMMER("夏天", "映日荷花别样红"){
		@Override
		public String getInfo() {
			
			return "^^B^^";
		}
	},
	AUTUMN("秋天", "秋水共长天一色"){
		@Override
		public String getInfo() {
			
			return "^^C^^";
		}
	},
	WINTER("冬天", "窗含西岭千秋雪"){
		@Override
		public String getInfo() {
			
			return "^^D^^";
		}
	};
	
	final String SEASON_NAME;
	final String SEASON_DESC;
	
	Season4(String seasonName,String seasonDesc){
		this.SEASON_NAME = seasonName;
		this.SEASON_DESC = seasonDesc;
	}


}
