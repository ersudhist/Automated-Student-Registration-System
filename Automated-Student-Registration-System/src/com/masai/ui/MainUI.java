package com.masai.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.masai.dao.dao;
import com.masai.dao.daoImpl;
import com.masai.services.AdminAddNewCourse;
import com.masai.services.AdminAllocateStudentsInaBatchUnderaCourse;
import com.masai.services.AdminCreatBatchunderaCourse;
import com.masai.services.AdminDeleteCourse;
import com.masai.services.AdminDisplayCourseInfo;
import com.masai.services.AdminUpdateFeesOfCourse;
import com.masai.services.AdminUpdateTotalSeatsOfaBatch;
import com.masai.services.AdminViewStudentsOfEveryBatch;
import com.masai.services.LoginStudent;
import com.masai.services.RegisterStudentCourseBatch;

public class MainUI {

	public static void main(String[] args) throws Exception {
		System.out.println();
		System.out.println(" Welcome to Automated Student registration system!");
		dao daoObj = new daoImpl();

		Scanner sc = new Scanner(System.in);
		
		daoObj.displayHomePageOptionToUser();

		try {
			
		int profile = sc.nextInt(); 
		
		if(profile == 1){
			
			if(daoObj.checkForAdmin()) {
				daoObj.displayOptionsForAdmin();
				
				int aOpt = sc.nextInt();
				
				switch (aOpt) {
				case 1: {
					AdminAddNewCourse a = new AdminAddNewCourse();
					break;
				}
				case 2: {
					AdminUpdateFeesOfCourse a = new AdminUpdateFeesOfCourse();
					break;

				}
				case 3: {
					AdminDeleteCourse a = new AdminDeleteCourse();
					break;

				}
				case 4: {
					AdminDisplayCourseInfo a = new AdminDisplayCourseInfo();
					break;

				}
				case 5: {
					AdminCreatBatchunderaCourse a = new AdminCreatBatchunderaCourse();		
					break;
				}
				case 6: {
					AdminAllocateStudentsInaBatchUnderaCourse a = new AdminAllocateStudentsInaBatchUnderaCourse();
					break;
				}
				case 7: {
					AdminUpdateTotalSeatsOfaBatch a = new AdminUpdateTotalSeatsOfaBatch();
					break;
				}
				case 8: {
					AdminViewStudentsOfEveryBatch a = new AdminViewStudentsOfEveryBatch();
					break;
				}
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + aOpt+ " enter valid options please.");
				}
			}
			else System.out.println("Please enter correct ID and Password!");
			
		}
		
		else if(profile == 2) {
			RegisterStudentCourseBatch rscb = new RegisterStudentCourseBatch();
		}
		else if(profile == 3) {
			LoginStudent ls = new LoginStudent();
		}

		else System.out.println("Please enter valid input 1, 2 or 3");
		
		}
		catch(InputMismatchException ime) {
			System.out.println("Please enter valid input");
		}
		
		
	}

}
