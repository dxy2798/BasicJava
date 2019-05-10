package IOTest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
public class TestDataStream {

	public static void main(String[] args) {
		//这一步做了两件事.1、在内存分配一个字节数组 2、把baos作为一根管子插到内存数组上
		ByteArrayOutputStream baos = 
				new ByteArrayOutputStream();
		//把dos套接到baos上
		DataOutputStream dos =
				new DataOutputStream(baos);
		try{
			dos.writeDouble(Math.random());  //向内存数组内写入一个8字节的随机值
			dos.writeBoolean(true);          //向内存数组内写入一个1字节的布尔值
			
			ByteArrayInputStream bais = //toByteArray()将baos转为一个字节数组
					new ByteArrayInputStream(baos.toByteArray());
			System.out.println(bais.available());//返回可从此输入流读取（或跳过）的剩余字节数
			DataInputStream dis = new DataInputStream(bais);
			//先写的一定要先读出来,先进先出.
			System.out.println(dis.readDouble());
			System.out.println(dis.readBoolean());
			dos.close();dis.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
