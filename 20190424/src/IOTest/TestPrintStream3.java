package IOTest;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

public class TestPrintStream3 {

	public static void main(String[] args) {
		String s = null;
//		BufferedReader br = 
//				new BufferedReader(new InputStreamReader(System.in));
		//上面相当于分开写: 1、一根管子(InputStreamReader)接到标准输入上		
		InputStreamReader isr = new InputStreamReader(System.in);
		// 2、在外面套一根管子(ufferedReader)
		BufferedReader br = new BufferedReader(isr);
		
		try{
			// 一根管子(FileWriter)怼到具体文件上,true 说明是添加在文件末尾
			FileWriter fw = 
					new FileWriter("d:\\logfile.log",true);
			// 一根管子 (PrintWriter) 怼在 FileWrinter 上
			PrintWriter log = new PrintWriter(fw);
			
			while((s=br.readLine()) != null){
				if(s.equalsIgnoreCase("exit")){
					break;
				}
				System.out.println(s.toUpperCase());
				log.println("=====");
				log.println(s.toUpperCase());
				log.flush();
			}
			log.println("===" + new Date() + "===");
			log.flush();
			log.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
