package AnnotationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AnnotationTest {

	@SuppressWarnings("rawtypes")
	@Test
	public void helloAnnotation() {
		@SuppressWarnings("unused")
		List list = new ArrayList();
	}

	@HelloAnnotation(age = 12, major = "Java")
	class A{
		@HelloAnnotation(age = 13, major = "Oracle")
		void test(){}
		
		void test2(@Deprecated String s){}
		
		@Deprecated
		void test3(){}
		
	}
	
	class B extends A{
		
		@Override
		void test() {
			super.test();
		}
	}
	
}
