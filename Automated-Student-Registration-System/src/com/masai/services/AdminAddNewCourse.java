package com.masai.services;

import com.masai.dao.dao;
import com.masai.dao.daoImpl;

public class AdminAddNewCourse {

	public AdminAddNewCourse() {
		dao daoObj = new daoImpl();
		daoObj.adminAddNewCourse();
	}
	
}
