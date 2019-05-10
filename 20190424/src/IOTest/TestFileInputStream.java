package IOTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class TestFileInputStream {

	public static void main(String[] args) {
		
		int b = 0;
		//FileInputStream in = null;
		FileReader fr = null;
		try{
//			in = new FileInputStream("D:\\GitRepository\\20190424\\src\\IOTest\\TestFileInputStream.java");
			fr = new FileReader("D:\\GitRepository\\20190424\\src\\IOTest\\TestFileInputStream.java");
		}catch(FileNotFoundException e){
			System.out.println("找不到指定文件");
			System.exit(-1);
		}
		
		try{
			long num = 0;
			//while((b = in.read()) != -1){
			while((b = fr.read()) != -1){
				System.out.print((char)b);
				num++;
			}
			//in.close();
			fr.close();
			System.out.println();
			System.out.println("共读取了" + num + "个字节");
		}catch(IOException e1){
			System.out.println("文件读取错误");
			System.exit(-1);
		}
		
	}

}
