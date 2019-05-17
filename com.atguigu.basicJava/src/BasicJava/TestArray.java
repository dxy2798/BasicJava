package BasicJava;

public class TestArray {

	public static void main(String[] args) {

/*		int[] scores = {90,67,89,51};
		for(int i = 0; i < scores.length; i++){
			System.out.println(scores[i]);
		}*/
		
		//String [] names = new String[5];
		
		//声明一个二维数组: test 的元素是一个一维数组
/*		int [][] test = new int[5][];
		
		for(int i = 0;i<test.length;i++){
			//System.out.println(test[i]);
			
			//为二维数组的每一个元素(即一维数组)分配内存空间
			test[i] = new int[i + 1];
			for(int j = 0;j<test[i].length;j++){
				test[i][j] = i * j + 10;
				System.out.println(test[i][j]);
			}
		}*/
		
		/**
		 * 杨辉三角
		 */
		
		int [][] yh = new int[10][];
		
		for(int i = 0;i < yh.length;i++){
			yh[i] = new int[i + 1];
			
			for(int j = 0;j < yh[i].length;j++){
				yh[i][0] = 1;
				yh[i][yh[i].length -1] = 1;
				if(i > 1){
					if(j > 0 && j < yh[i].length -1){
						yh[i][j] = yh[i -1][j -1] + yh[i -1][j];
					}
				}
			}
		}
		
		for(int i = 0;i < yh.length;i++){
			for(int j = 0;j < yh[i].length; j++){
				System.out.print(yh[i][j] + " ");
			}
			System.out.println("");
		}
		

	}

}
