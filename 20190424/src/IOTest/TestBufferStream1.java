package IOTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestBufferStream1 {

	public static void main(String[] args) {
		try{
			// 文件输入流 节点流
			FileInputStream fis = 
					new FileInputStream("D:\\GitRepository\\20190424\\src\\IOTest\\TestFileInputStream.java");
			//缓冲输入流
			BufferedInputStream bis =
					new BufferedInputStream(fis);
			int c = 0;
			System.out.println(bis.read());  // p 第一个字母
			System.out.println(bis.read());  // a 第二个字母
			bis.mark(20);
			for(int i=0;i<10 && (c=bis.read()) != -1;i++){
				System.out.print((char)c + " ");
			}
			System.out.println();
			bis.reset();
			for(int i=0;i<10 && (c=bis.read()) != -1;i++){
				System.out.print((char)c + " ");
			}
			bis.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
