package com.atguigu.javase.lesson7;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class TestStudentScoreSort {


	
	public static void main(String[] args) {
		
		final int flag = inputSortFlag();
		TreeSet set = getTreeSet(flag);
		inputStudents(set);
		printSet(set);
	}

	private static void printSet(TreeSet set) {
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	
	private static void inputStudents(TreeSet set) {
		Scanner sc = new Scanner(System.in);
		for(int i = 0;i < 5; i++){
			System.out.println("请输入学生姓名: ");
			String name = sc.next();
			System.out.println("请输入学生分数: ");
			sc = new Scanner(System.in);
			int score = sc.nextInt();	
			set.add(new Student1(name, score));
		}
	}

	private static TreeSet getTreeSet(final int flag) {
		//获取排序规则
		Comparator comparator = new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Student1 && o2 instanceof Student1){
					Student1 stu1 = (Student1) o1;
					Student1 stu2 = (Student1) o2;
					if(flag == 1){
						return stu1.getScore() - stu2.getScore();
					}else if(flag == 2){
						return stu2.getScore() - stu1.getScore();
					}else if(flag == 3){
						return stu1.getName().compareTo(stu2.getName());
					}else if(flag == 4){
						return  stu2.getName().compareTo(stu1.getName());
					}					
				}
				
				throw new ClassCastException("无法转换为 Stedent1 类型");
			}
		};
		
		TreeSet set = new TreeSet(comparator);
		return set;
	}

	private static int inputSortFlag() {
		System.out.println("请选择排序方式: ");
		System.out.println("1.按分数升序 2.按分数降序 3.按名字升序 4.按名字降序");
		Scanner sc = new Scanner(System.in);
		int flag = sc.nextInt();
		return flag;
	}

}
