package IOTest;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestBufferStream2 {

	public static void main(String[] args) {
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\dat2.txt"));
			BufferedReader br = new BufferedReader(new FileReader("D:\\dat2.txt"));
			String s = null;
			for(int i=1;i<=100;i++){
				s = String.valueOf(Math.random());
				bw.write(s);
				bw.newLine();
			}
			bw.flush();
			while(br.readLine() != null){
				s = br.readLine();
				System.out.println(s);
			}
//			while((s=br.readLine()) != null){
//				System.out.println(s);
//			}
			bw.close();
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
