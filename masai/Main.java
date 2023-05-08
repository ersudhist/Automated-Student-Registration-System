package com.masai;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("1. Select 1 for Admin Login");
        System.out.println("2. Select 2 for Student Login");

        
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        
        
		System.out.println("Welcome , in Product Management System");

		try {

			int preference = 0;
			do {
				System.out.println("Please enter your preference, \n" + " '1' --> Admin login ,"+"\n"+" '2' --> Student login , \n"
				+ "'3' for Customer signup, '0' for exit");
				preference = scanner.nextInt();
				switch (preference) {
				case 1:
					//adminFunctionality(sc, products, customers, transactions);
					break;
				case 2:
					//customerFunctionality(sc, customers, products, transactions);
					break;

				case 3:
					//customerSignup(sc, customers);
					break;

				case 0:
					System.out.println("successfully existed from the system");

					break;

				default:
					throw new IllegalArgumentException("Invalid Selection");
				}

			}

			while (preference != 0);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
//        if (username.equals("admin") && password.equals("admin")) {
//            boolean exit = false;
//            while (!exit) {
//                System.out.println("\nPlease select an option:");
//                System.out.println("1. Add new course");
//                System.out.println("2. Search for information about courses");
//                System.out.println("3. Update details of course");
//                System.out.println("4. Create a batch under a course");
//                System.out.println("5. Search for information about batches");
//                System.out.println("6. Update details of batch");
//                System.out.println("7. View student details");
//                System.out.println("8. View student list of a batch");
//                System.out.println("9. Consolidate report batch-wise and course-wise");
//                System.out.println("10. Exit");
//                
//                int choice = scanner.nextInt();
//                scanner.nextLine();
////                switch (choice) {
////                    case 1:
////                        addCourse(scanner);
////                        break;
////                    case 2:
////                        searchCourses(scanner);
////                        break;
////                    case 3:
////                        updateCourse(scanner);
////                        break;
////                    case 4:
////                        createBatch(scanner);
////                        break;
////                    case 5:
////                        searchBatches(scanner);
////                        break;
////                    case 6:
////                        updateBatch(scanner);
////                        break;
////                    case 7:
////                        viewStudents();
////                        break;
////                    case 8:
////                        viewBatchStudents(scanner);
////                        break;
////                    case 9:
////                        consolidateReport();
////                        break;
////                    case 10:
////                        exit = true;
////                        break;
////                    default:
////                        System.out.println("Invalid choice, please try again");
////                }
//            }
//        } else {
//            System.out.println("Invalid username or password, access denied.");
//        }
    
	}

	
}

