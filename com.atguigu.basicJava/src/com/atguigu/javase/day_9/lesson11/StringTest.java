package com.atguigu.javase.day_9.lesson11;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.junit.Test;
/**
 * 1. String 是不可变得字符序列!
 * 2. 关于字符串缓冲池: 直接通过 = 为字符串赋值,会先在缓冲池中查找有没有一样的字符串
 *    若有就把那个引用赋给字符串变量,否则会创建一个新的字符串,并把对应的字符串放入缓冲池.
 * 3. 字符串的几个常用方法:
 * 3.1 去除前后空格的 trim() 方法. 
 * 3.2 求子字符串的方法: subString()  
 * 3.3 subString(fromIndex,toIndex): [fromIndex,toIndex)
 * 3.4 indexOf: 求指定字符的索引	  
 * 3.5 split(String regex): 把字符串拆分成字符串数组.
 * 3.6 equals(): 比较字符串内容是否相等必须使用该方法,而不能直接使用 ==
 */
public class StringTest {

	/**
	 * 给定一个字符串:acmfn
	 * 经过运算得到:bdngo
	 * 若某个字符已经是 z ,则返回 a .
	 */
	
	@Test
	public void testTransforString(){
		String str = "acmfnz&Acc10ee-EMM";
		System.out.println(str);
		
		for(int i = 0; i < str.length(); i++){
			char ch = str.charAt(i);
			if(ch >= 'a' &&  ch <= 'z'){
				if(ch == 'z')
					ch = 'a';
				else
					ch = (char) (1 + ch);
			}else if(ch >= 'A' && ch <= 'Z'){
				if(ch == 'Z')
					ch = 'A';
				else
					ch = (char) (1 + ch);
			}
			System.out.print(ch);
		}
		
	}
	
	@Test
	public void testMove(){
        System.out.println(move("acmfnz&Acc10ee-EMM"));
	}
	
	
	public String move(String string){
	
		int len = string.length();
		String temp2 = null;
		for(int i = 0;i < len; i++){
			
			char temp = string.charAt(i);
			int b = temp;
			int c = 0;
			if(b == 122){
				c = 97;
			}else{
				c = b + 1;
			}
			if(temp2 == null){
				temp2 = Character.toString((char) c);
			}else{
			    temp2 = temp2 + Character.toString((char) c);
			}
		
		}
		return temp2;
		
	}
	
	
	
	
	
	@Test
	public void testSplit(){
		
		String str = "a-b-c-d-e-f-g";
		
		String [] values = str.split("-");
		
		for(int i =0;i < values.length;i++){
			
			System.out.println(values[i]);
		}
		
	}
	
	@Test
	public void testIndexOf(){
		String str = "http://www.atguigu.com/index.jsp?name=Tom";
		
		int beginIndex = str.indexOf("//") + 2;
		int endIndex = str.lastIndexOf("/");
		System.out.println(str.substring(beginIndex, endIndex));
		
	}
	
	@Test
	public void testSubString(){
		String str = "http://www.atguigu.com/index.jsp?name=Tom";
		String str1 = str.substring(0);
		System.out.println(str1);
		
		String str2 = str.substring(1, 5);
		System.out.println(str2);
	}
	
	
	@Test
	public void testTrim(){
		String str = " ab cd ";
		System.out.println("--" + str + "--");
		
		String str2 = str.trim();
		System.out.println("--" + str + "--");
		System.out.println("--" + str2 + "--");
	}
	
	
	@Test
	public void testNewString(){
		
		String str1 = "hello world";
		String str2 = "hello world";
		
		System.out.println(str1 == str2);
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		
		String str3 = new String("abcde");
		String str4 = new String("abcde");
		System.out.println(str3 == str4);
		System.out.println(str3.hashCode());
		System.out.println(str4.hashCode());
	}
	
	
	@Test
	public void test1(){
		String str = "www.atguigu.com";
		str = str.replace('c', 'm');
		System.out.println(str);
		
	}
	
	@Test
	public void test() {
		String str = "www.atguigu.com";
		
		String result = str.replace('c', 'm');
		str.replace('c', 'm');
		System.out.println(str);
		System.out.println(result);
	}
	
	@Test
	public void testPassRef(){
		Person person = new Person("ABC",12);
		System.out.println(person);
		
		changePerson(person);
		System.out.println(person);
		
		String str = "abcd";
		System.out.println(str);
		changeString(str);
		System.out.println(str);
		
	}
	
	

	public void changePerson(Person person){
		person.setName("atguigu");
	}
	
	public void changeString(String str){
		str.replace('a', 'b');
	}
}
