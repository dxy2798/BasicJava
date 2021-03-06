package com.atguigu.jdbc;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

public class DAOTest {

	DAO dao = new DAO();
	@Test
	public void testUpdate() {
		String sql = "INSERT INTO customers(name,"
				+ "email,birth) VALUES(?,?,?)";
		dao.update(sql, "XiaoMing","xiaoming@atguigu.com",
				new Date(new java.util.Date().getTime()));
		
	}

	@Test
	public void testGet() {
		 String sql = "SELECT flow_id flowid,type,id_card idCard," 
					+ "exam_card examCard,student_name studentName,"
					+"location,grade " + "FROM examstudent WHERE flow_id = ?";
			Student stu = dao.get(Student.class, sql, 1);
			System.out.println(stu);
	}

	@Test
	public void testGetForList() {
		 String sql = "SELECT flow_id flowid,type,id_card idCard," 
					+ "exam_card examCard,student_name studentName,"
					+"location,grade " + "FROM examstudent";
		 List<Student> students = dao.getForList(Student.class, sql);
		 System.out.println(students);
	}

	@Test
	public void testGetForValue() {
		 String sql = "SELECT count(id) FROM ajax.customers";
		 long id_Count = dao.getForValue(sql);
		 System.out.println(id_Count);
	}

}
