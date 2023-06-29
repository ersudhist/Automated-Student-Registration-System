package com.masai.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.dao;
import com.masai.dao.daoImpl;
import com.masai.dto.Course;
import com.masai.exceptions.CourseException;

public class AdminDeleteCourse {
	public AdminDeleteCourse() throws SQLException, CourseException {
		Scanner sc = new Scanner(System.in);
		dao daoObj = new daoImpl();
		List<Course> lisOfCourses = daoObj.getListOfCourses();
		System.out.println("List of courses-");
		lisOfCourses.forEach(c ->{
			System.out.println(c.getCname());
		});
		System.out.println("Enter course name to delete:");
		String cName = sc.next();
		daoObj.adminDeleteCourse(cName);
	}


}
