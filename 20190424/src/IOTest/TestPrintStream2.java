package IOTest;

import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class TestPrintStream2 {

	public static void main(String[] args) {
		//String filename = args[0];
		String filename = "D:\\GitRepository\\20190424\\src\\IOTest\\TestFileInputStream.java";
		if(filename != null){
			list(filename,System.out);
		}
		
	}

	private static void list(String f, PrintStream fs) {
		try{
			BufferedReader br = 
					new BufferedReader(new FileReader(f));
			String s = null;
			while((s=br.readLine()) != null){
				fs.println(s);
			}
			br.close();
		}catch(IOException e){
			System.out.println("无法读取文件");
		}
		
	}

}
