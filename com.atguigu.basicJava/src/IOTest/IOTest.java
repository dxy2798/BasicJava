package IOTest;

import java.awt.peer.SystemTrayPeer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

public class IOTest {
	
	/**
	 * 使用 RandomAccessFile.
	 * 向 hello.txt 文件中插入一行: I Love Gongfu....
	 * 插入到第二行,原内容下移.
	 * @throws IOException 
	 */
	@Test
	public void testRandomAccessFile2() throws IOException{
		RandomAccessFile access = new RandomAccessFile("hello.txt", "rw");
		System.out.println(access.length());
		
	}
	
	
	@Test
	public void testRandomAccessFile() throws IOException{
		
		//1. 创建 RandomAccessFile 类
			RandomAccessFile access = new RandomAccessFile("hello.txt", "rw");
		
		//2. 对文件进行读写操作
			String str = null;
			while((str = access.readLine()) != null){
				System.out.println(str);
			}
			//向文件结尾写入 www.atguigu.com
			long pointer = access.getFilePointer();
			System.out.println(pointer);
			access.seek(433);
			//会覆盖原有文件.
			//access.writeBytes("www.atguigu.com");
			
		
		//3. 关闭 RandomAccessFile 类
			access.close();
	}
	
	
	@Test
	public void testInputObjectStream() throws IOException, ClassNotFoundException{
		// 使用 ObjectInputStream 把从硬盘上读取对象
		InputStream in = new FileInputStream("d:\\obj.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(in);
		
		Object obj = objectInputStream.readObject();
		
		System.out.println(obj);
		
		objectInputStream.close();
		in.close();
	}
	
	@Test
	public void testSerializable() throws IOException{
		Person person = new Person("AA", 12,new Address("Beijing"));
		
		// 使用 ObjectOutputStream 把对象写到硬盘上
		OutputStream out = new FileOutputStream("d:\\obj.txt");
		
		ObjectOutputStream objectOutoutStream = 
				new ObjectOutputStream(out);
		
		objectOutoutStream.writeObject(person);
		
		objectOutoutStream.close();
		out.close();
	}
	
	/**
	 * 先创建两个字节输入输出流: 分别指向 hello.txt 和 hello5.txt
	 * 然后再转为字符输入输出流
	 * 再转为带缓冲的输入输出流
	 * 
	 * 完成文件的复制
	 * @throws IOException 
	 */
	@Test
	public void testOutputStreamWriter() throws IOException{
		InputStream in = new FileInputStream("hello.txt");
		OutputStream out = new FileOutputStream("hello5.txt");
		
		Reader reader = new InputStreamReader(in);
		Writer writer = new OutputStreamWriter(out);
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		
		String str = null;
		int i = 0;
		while((str = bufferedReader.readLine()) != null){
			if(i != 0){
				bufferedWriter.write("\n");
			}
			bufferedWriter.write(str);
			i++;
		}
		
		// 输入的从里往外关.
		in.close();
		reader.close();
		bufferedReader.close();
		// 输出的从外往里关.
		bufferedWriter.close();
		writer.close();
		out.close();

	}
	
	/**
	 * 字节流转为字符流
	 * @throws IOException
	 */
	@Test
	public void testInputStreamReader() throws IOException{
		// 指向文档的字节流
		InputStream in = new FileInputStream("hello.txt");
		
		// 把上面的字节流转为字符流
		Reader reader = new InputStreamReader(in);
		
		// 把字符流转为缓冲的字符流
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		String str = null;
		while((str = bufferedReader.readLine()) != null){
			System.out.println(str);
		}
		
		
		// 关闭: 从里往外关.
		in.close();
		reader.close();
		bufferedReader.close();
		
		
	}
	
	
	
	/**
	 * 利用 BufferedInputStream 和 BufferedOutStream 完成
	 * hello.txt 到 hello5.txt 文件的复制.
	 * @return 
	 * @throws IOException 
	 */
	@Test
	public void testBufferedInputStreamAndBufferedOutStream() throws IOException{
		//1. 创建 BufferedInputStream 和 BufferedOutStream
		InputStream in = new FileInputStream("新版通讯录.xls");
		BufferedInputStream bufferednIputStream = new BufferedInputStream(in);
		
		OutputStream out = new FileOutputStream("新版通讯录1.xls");
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
		
		//2. 进行读写操作
		
		byte [] buffer = new byte[1024];
		int len = 0;
		while((len = bufferednIputStream.read(buffer)) != -1){
			bufferedOutputStream.write(buffer,0,len);
		}

		//3. 关闭 IO 流
		bufferedOutputStream.close();
		bufferednIputStream.close();
	}
	
	
	

	/**
	 * 复制 hello.txt 为 hello4.txt
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testBufferedReaderAndBufferedWriter() throws IOException{
		//1. 创建 BufferedReader 和 BufferedWriter
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader("hello.txt"));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("hello4.txt"));

		//2. 进行读写操作
			String str = null;
			int i = 0;

			
			while((str = bufferedReader.readLine()) != null){
				if(i != 0){
					bufferedWriter.write("\n");
				}
				bufferedWriter.write(str);
				i++;
			}
			
		//3. 关闭 IO 流,直接关闭包装流,内部会关闭节点流
		bufferedReader.close();
		bufferedWriter.close();
	}
	
	
	
	/**
	 * 利用字符输入输出流,完成 hello.txt 文件的复制
	 * 把该文件复制为 hello2.txt
	 * @throws IOException 
	 */
	@Test
	public void testCopyByReaderAndWrinter() throws IOException{
		//1. 创建字符输入输出流
			Reader reader = new FileReader("hello.txt");
			Writer writer = new FileWriter("hello2.txt");
			
		//2. 创建一个字符数组.
			char [] buffer = new char[10];
			
		//3. 利用循环读取源文件,并向目标文件写入
			int len = 0;
		    while((len = reader.read(buffer)) != -1){
		    	writer.write(buffer,0,len);
		    	System.out.println(len);
		    }
		//注意: 使用的写入的方法:write(char[] cbuf, int off, int len) 
		reader.close();
		writer.close();
	}
	
	
	
	/**
	 * 利用字节输入输出流,完成 hello.txt 文件的复制
	 * 把该文件复制为 hello2.txt
	 * @throws IOException 
	 */
	
	@Test
	public void testCopyFile() throws IOException{
		//1. 创建定位到  hello.txt 的文件的输入流.
		InputStream in = new FileInputStream("尚硅谷_佟刚_Java基础_OutputStream&文件复制.wmv");
		
		//2. 创建定位到  hello2.txt 的文件的输出流.
		OutputStream out = new FileOutputStream("尚硅谷_佟刚_Java基础_OutputStream&文件复制2.wmv");
		
		//3. 创建一个 byte 数组用于读写文件.
		byte [] buffer = new byte[1024 * 10];
		int len = 0;
		
		//4. 读写文件:
		// in.read(buffer); out.write(buffer,0,len);
		while((len = in.read(buffer)) != -1){
			System.out.println(len);
			out.write(buffer, 0, len);
		}
		//5. 关闭流资源.
		out.close();
		in.close();
	}
	
	/**
	 * 测试字节输出流
	 * @throws IOException
	 */
	@Test
	public void testOutputStream() throws IOException{
		OutputStream out = new FileOutputStream("abcd.txt");
		
		String content = "www.atguigu.com\nHello Java!";
//		int len = 10;
//		
//		byte [] contentBytes = content.getBytes();
//		
//		for(int i = 0; i < content.length() / 10; i++){
//			//把 String 拆分成多个 buffer
//			out.write(contentBytes, i * 10, len);
//		}
//		
//		if(content.length() % 10 != 0){
//			out.write(contentBytes, 10 * (content.length() / 10), 
//					content.length() - 10 * (content.length() / 10));
//		}
		
		out.write(content.getBytes());
		
		
		out.close();
	}
	
	
	
	@Test
	public void testReader() throws IOException {
		//利用字符输入流读取 hello.txt 文档的内容, 输出到控制台
		Reader reader = new FileReader("hello.txt");
		
		char [] buffer = new char[10];
		int len = 0;
		
		while((len = reader.read(buffer)) != -1){
			for(int i = 0; i < len;i++){
				System.out.print(buffer[i]);
			}
		}

		reader.close();
	}
	
	
	@Test
	public void testInputStream() throws IOException {
		//1. 创建一个字节输入流
		InputStream in = new FileInputStream("hello.txt");
		//2. 读取文件的内容
		//2.1 一次读取一个字节,效率很低,-1表示读取到文件的结尾
/*		int result = in.read();
		while(result != -1){
		System.out.print((char)result);
		result = in.read();
		}*/
		//2.2 一次读取一组字符.
/*		byte[] buffer = new byte[10];
		int len = 0;
		//返回读取的字节数,若为 -1 表示读取到文件的结尾
		while((len = in.read(buffer)) != -1){
			for(int i = 0;i < len;i++){
				System.out.print((char)buffer[i]);
			}
		}*/
		
		//2.3 把内容读取到字节数组的部分连续的元素中
		byte[] result = new byte[1024 * 10];
		in.read(result, 10, in.available());
		
		//3. 关闭连接
		in.close();
	}
	
	
	
	/**
	 * File: 代表物理意义的文件或目录
	 * @throws IOException 
	 * 
	 */
	@Test
	public void testFile() throws IOException {
		//1. 创建 File 对象
		File file = new File("hello.txt");
		//2. 测试 File 对象的方法.
		//2.1 文件名相关的方法.
		String fileName = file.getName();
		System.out.println(fileName);
		
		//2.2 访问文件的绝对路径
		String path = file.getAbsolutePath();
		System.out.println(path);
		
		//2.3 为文件重命名
		//file.renameTo(new File("d:\\hello.txt"));
		
		//3. 文件检测相关的方法
		System.out.println(file.exists());
		File dir = new File("atguigu");
		System.out.println(dir.isDirectory());
		
		//4. 获取文件的常规信息.
		System.out.println(file.length());
		
		//5. 文件操作相关.
		File file2 = new File("abcd.txt");
		file2.createNewFile();
	}

}
