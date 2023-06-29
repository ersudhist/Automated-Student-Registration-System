package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masai.dto.Admin;
import com.masai.dto.BatchRecord;
import com.masai.dto.Course;
import com.masai.dto.Student;
import com.masai.dto.BatchSeat;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.StudentException;
import com.masai.utility.DButils;

public class daoImpl implements dao{

	private static final String sEmail = null;
	private static final String sName = null;
	private static final String sPassword = null;
	private static final Student s = null;

	@Override
	public String registerStudent(Student student) throws StudentException, SQLException {
		
		String answer = "Student already exists!";
		String sEmail = student.getsEmail();
		String sPassword = student.getsPassword();
		String sName = student.getsName();
		
		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into students values (?, ?, ?)");
			ps.setString(1, sEmail);
			ps.setString(2, sPassword);
			ps.setString(3, sName);
			
			int x = ps.executeUpdate();
			answer = x + " student statements inserted";
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return answer;
		
	}
	
	@Override
	public boolean registerBatch(int cId, String cName, String sEmail) throws Exception {
		boolean answer = false;
		int lbatchId;
		int ltotalSeats;
		int lseatsFilled;
		int fbatchId;
		
		dao daoObj = new daoImpl();

		try(Connection conn = DButils.getConnection()){
			
			boolean flag = false;
			boolean used = false;
			PreparedStatement ps = conn.prepareStatement("select bId, totalSeats, seatsFilled from batchSeats where cId = ?");
			ps.setInt(1, cId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				lbatchId = rs.getInt("bId");
				ltotalSeats = rs.getInt("totalSeats");
				lseatsFilled = rs.getInt("seatsFilled");

				if(lseatsFilled < ltotalSeats && used == false) {
					used = true;
					flag = true;
					answer = true;
					fbatchId = lbatchId;
					

					PreparedStatement ps3 = conn.prepareStatement("update batchSeats set seatsFilled = (seatsFilled + 1) where cId = ? AND bId = ?");
					ps3.setInt(1, cId);
					ps3.setInt(2, lbatchId);
					int x2 = ps3.executeUpdate();
					
					
					PreparedStatement ps2 = conn.prepareStatement("insert into batch (batchNo, cId, sEmail) values (?, ?, ?)");
					ps2.setInt(1, fbatchId);
					ps2.setInt(2, cId);
					ps2.setString(3, sEmail);
					int x = ps2.executeUpdate();
					
					System.out.println("Student"+ daoObj.getSNameFromEmail(sEmail) +"with e-mail "+ sEmail +" registered into course "+ cName );
				}
			}
			if(!flag) {
				throw new CourseException("Seats not available for course " + cName +" (or) batch not allocated for the course yet.");

			}
			
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return answer;
		
	}
	
	@Override
	public boolean checkForStudent(String sEmail, String sPassword) throws StudentException {
		boolean answer = false;

		try(Connection conn = DButils.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from students where sEmail= ? AND sPassword = ?");
			ps.setString(1, sEmail);
			ps.setString(2, sPassword);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				answer = true;
			}
			
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		return answer;
	}

	@Override
	public List<Course> getListOfCourses() throws SQLException, CourseException {
		
		List<Course> listOfCourses = new ArrayList<>();
		boolean isEmpty = true;
		
		try(Connection conn = DButils.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from courses");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				isEmpty = false;
				int cId = rs.getInt("cId");
				String cName = rs.getString("cName");
				int fees = rs.getInt("fees");
				String cInfo = rs.getString("cInfo");
				
				Course c = new Course(cId, cName, fees, cInfo);
				listOfCourses.add(c);
			}
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		if(isEmpty)throw new CourseException("No course present");
		
		return listOfCourses;
		
		
		
	}


	@Override
	public boolean checkForCourse(String cName) throws SQLException, CourseException {
		boolean answer = false;

		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select cName from courses where cName = ?");
			ps.setString(1, cName);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())answer = true;
			
			
			
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return answer;
	}


	@Override
	public int getCourseId(String cName) throws SQLException, CourseException {
		
		int answer = 0;
		
		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select cId from courses where cName = ?");
			ps.setString(1, cName);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				answer = rs.getInt("cId");
			}
			else throw new CourseException("There's no "+ cName + " course. Please enter proper course name.");
			
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return answer;
		
	}


	@Override
	public BatchRecord getBatchRecordForStudent(String sEmail) {
		
		BatchRecord brdto = null;
		
		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from batch where sEmail = ?");
			ps.setString(1, sEmail);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			int batchUid = rs.getInt("batchUid");
			int batchNo = rs.getInt("batchNo");
			int cId = rs.getInt("cId");
			String sEmailr = rs.getString("sEmail");
			
			brdto = new BatchRecord(batchUid, batchNo, cId, sEmailr);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return brdto;
		
	}

	@Override
	public boolean studentDetailEdit(String sEmail, String sPaswword, String sNewPassword, String sNewName, String sNewCourseName) throws SQLException, CourseException 
	{

		boolean answer = false;
			dao daoObj = new daoImpl();
			BatchRecord br = null;
			
		
			
			try(Connection conn = DButils.getConnection()){
				int newCId = daoObj.getCourseId(sNewCourseName);
				daoObj.registerBatch(newCId, sNewCourseName, sEmail);
				answer = true;
				
				if(daoObj.getBatchRecordForStudent(sEmail) != null) {
					br = daoObj.getBatchRecordForStudent(sEmail);
					int batchUid = br.getBatchUid();
					int batchNo = br.getBatchNo();
					
					//caution delete statements ahead
					if((Integer)br.getBatchNo() != null) {
						PreparedStatement ps = conn.prepareStatement("delete from batch where batchUid = ?");
						ps.setInt(1, batchUid);
						int x = ps.executeUpdate();

						PreparedStatement ps2 = conn.prepareStatement("update batchSeats set seatsFilled = (seatsFilled-1) where bId = ?");
						ps2.setInt(1, batchNo);
						ps2.executeUpdate();
						
					}
				}												
				
			}
			
			catch (SQLException e) {
				System.out.println("check");
				throw new SQLException(e.getMessage());
			} catch (CourseException e) {
				System.out.println("check");
				throw new CourseException(e.getMessage());
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			return answer;
			
	}

	@Override
	public void editStudentProfile(String sEmail, String nSPassword, String nSName) throws SQLException {
		
		dao daoObj = new daoImpl();

		try(Connection conn = DButils.getConnection()){
			
				PreparedStatement ps = conn.prepareStatement("update students set sPassword = ?, sName = ? where sEmail = ? ");
				ps.setString(1, nSPassword);
				ps.setString(2, nSName);
				ps.setString(3, sEmail);
				ps.executeUpdate();
				System.out.println("Student "+ daoObj.getSNameFromEmail(sEmail) +" with e-mail "+ sEmail +" updated with new password and name");
				
			}
		catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
			
		}


	@Override
	public List<BatchSeat> getBatchSeatdetails() throws SQLException {
		List<BatchSeat> listOfBS = new ArrayList<>();
		BatchSeat bs = null;
		
		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from batchSeats");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				int cId = rs.getInt("cId");
				int totalSeats = rs.getInt("totalSeats");
				int SeatsFilled = rs.getInt("SeatsFilled");

				bs = new BatchSeat(bId, cId, totalSeats, SeatsFilled);
				listOfBS.add(bs);
				
			}
		
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return listOfBS;
		
	}

	@Override
	public String getCourseName(int cId) throws SQLException {
		
		String cName = null;
		
		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select Cname from courses where cId = ?");
			ps.setInt(1, cId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				cName = rs.getString("cName");
			}
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		
		return cName;
		
	}

	@Override
	public void displayCourseAvailableWithOrWithoutSeats(String option) throws SQLException {

		dao daoObj = new daoImpl();
		List<BatchSeat> listOfBS;
		try {
		
		listOfBS = daoObj.getBatchSeatdetails();
		int n = listOfBS.size();
		int[] cIdIndex = new int[n];
		int[] seatsFilled = new int[n];
		int[] totalSeats = new int[n];
		
		for(int i=0; i<n; i++) {
			cIdIndex[i] = listOfBS.get(i).getcId();
			seatsFilled[i] = listOfBS.get(i).getSeatsFilled();
			totalSeats[i] = listOfBS.get(i).getTotalSeats();
		}
		
//		display with batch aggregation
		for(int i=0;i<n;i++) {
			for(int j=i+1; j<n; j++) {
				if(cIdIndex[i] == cIdIndex[j]) {
					seatsFilled[i] = seatsFilled[i] + seatsFilled[j];
					totalSeats[i] = totalSeats[i] +totalSeats[j];
					totalSeats[j] = 0;
					
				}
			}
		}
		
		if(option.equalsIgnoreCase("includeSeat"))
		{
		System.out.println("Course name  |  Seats available");

		for(int i=0; i<n; i++) {
			if(totalSeats[i] != 0 && (totalSeats[i] != seatsFilled[i]) && cIdIndex[i] > 0)
			System.out.println(daoObj.getCourseName(cIdIndex[i] ) +" | " + (totalSeats[i] - seatsFilled[i]) );
		}
		}
		
		else if(option.equalsIgnoreCase("includeslno"))
		{
		System.out.println("Sl.no -> Course name");
		int slno =1;
		for(int i=0; i<n; i++) {
			if(totalSeats[i] != 0&& (totalSeats[i] != seatsFilled[i]))
			{
				String cName = daoObj.getCourseName(cIdIndex[i]);
				if(cName!= null) {
					System.out.println(slno++ +" -> " + cName);

				}
			}			
		}
		}
		}
		
		catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
	}

	@Override
	public boolean checkForAdmin() {
		
		List<Admin> listOfAdmins = new ArrayList<>();
		listOfAdmins.add(new Admin("sk", "one"));
		//listOfAdmins.add(new Admin("a", "q"));
		boolean isPresent = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Login to profile-");
		System.out.println("Enter your User name:");
		String adminName = sc.next();
		System.out.println("Enter your password: ");
		String adminPassword = sc.next();
		
		Admin ad = new Admin(adminName, adminPassword);
		
		
		if(listOfAdmins.contains(ad))isPresent=true;
		
		return isPresent;
	}


	@Override
	public void displayOptionsForAdmin() {
		System.out.println();
		System.out.println("Welcome Admin!");
		System.out.println();
		System.out.println("Enter number to perform actions:");
		System.out.println();
		System.out.println("1. Add a new Courses");
		System.out.println("2. Update Fees of course.");	
		System.out.println("3. Delete  a course from any Training session.");
		System.out.println("4. Search information about a course.");
		System.out.println("5. Create Batch under a course.");
		System.out.println("6. Allocate students in a Batch under a course.");
		System.out.println("7. Update total seats of a batch.");
		System.out.println("8. View the students of every batch.");
		
	}

	@Override
	public void displayHomePageOptionToUser() {
		System.out.println();
		System.out.println();
		System.out.println(" Please choose your profile by entering the number below-");
		System.out.println();
		System.out.println(" 1. Administrator");
		System.out.println(" 2. Register in a course as a student");
		System.out.println(" 3. Login as student");
		
	}

	@Override
	public void adminAddNewCourse() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID:");
		int cId = sc.nextInt();
		System.out.println("Enter Course name :");
		String cName = sc.next();
		System.out.println("Enter Course fees:");
		int fees = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter course information:");
		String cInfo = sc.nextLine();
		
		try(Connection conn = DButils.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into courses values (?, ?, ?, ?)");
			ps.setInt(1, cId);
			ps.setString(2, cName.toUpperCase());
			ps.setInt(3, fees);
			ps.setString(4, cInfo);
			
			int x = ps.executeUpdate();
			
			System.out.println("Course "+ cName +" inserted into database successfully.");

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void adminUpdateFeesOfCourse(String cName, int fees) {
		dao daoObj = new daoImpl();
		
		try(Connection conn = DButils.getConnection()){
			int cId = daoObj.getCourseId(cName);
			PreparedStatement ps = conn.prepareStatement("update courses set fees = ? where cId = ?");
			ps.setInt(1, fees);
			ps.setInt(2, cId);
			ps.executeUpdate();
			
			System.out.println("Course "+cName +" is updated with fees "+ fees+".");
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (CourseException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void adminDeleteCourse(String cName) {
		dao daoObj = new daoImpl();

		try(Connection conn = DButils.getConnection()){
			int cId = daoObj.getCourseId(cName);

			PreparedStatement ps2 = conn.prepareStatement("delete from batchSeats where cId = ?");
			ps2.setInt(1, cId);
			PreparedStatement ps = conn.prepareStatement("delete from courses where cId = ?");
			ps.setInt(1, cId);
			ps2.executeUpdate();
			ps.executeUpdate();
			
			System.out.println(cName + " course deleted successfully.");
			System.out.println(cName + " deleted in every training session.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (CourseException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void adminDisplayCourseInfo(String cName) {
		dao daoObj = new daoImpl();

		try(Connection conn = DButils.getConnection()){
			int cId = daoObj.getCourseId(cName);

			PreparedStatement ps = conn.prepareStatement("select * from courses where cId = ?");
			ps.setInt(1, cId);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				int cIdl = rs.getInt("cId");
				String cNamel = rs.getString("cName");
				int fees = rs.getInt("fees");
				String cInfo = rs.getString("cInfo");
				System.out.println("The course information for course name you asked for-");
				System.out.println("Course information for "+ cName +" course-");
				System.out.println("CourseID: "+ cIdl);
				System.out.println("Course Name: "+ cNamel);
				System.out.println("Course fee: "+ fees);
				System.out.println("Course information: "+ cInfo);
			}
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (CourseException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void adminCreatBatchunderaCourse(String cName, int bId, int totalSeats ) {
		dao daoObj = new daoImpl();
		
		try(Connection conn = DButils.getConnection()){
			int cId = daoObj.getCourseId(cName);
			PreparedStatement ps = conn.prepareStatement("insert into batchSeats values (?, ?, ?, ?)");
			ps.setInt(1, bId);
			ps.setInt(2, cId);
			ps.setInt(3, totalSeats);
			ps.setInt(4, 0);
			ps.executeUpdate();
			System.out.println("Batch of batch ID "+ bId +" created under course "+ cName + " with seat capacity of "+ totalSeats);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (CourseException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public void adminAllocateStudentsInaBatchUnderaCourse() {

		dao daoObj = new daoImpl();
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = DButils.getConnection()){
			List<Student> listOfStudents = daoObj.getListOfStudents();
			if(listOfStudents.size()==0)System.out.println("No student has enrolled yet.");
			else {
				
				System.out.println("List of students in our institution-");
				System.out.println();
				listOfStudents.forEach(s -> System.out.println(s));
				System.out.println();
				System.out.println("Process to allocate student under a batch in a course starts.");
				System.out.println("Enter the Student E-mail: ");
				String sEmail = sc.next();
				String sPassword = daoObj.getStudentPassword(sEmail);
				
				boolean existence = daoObj.checkForStudent(sEmail, sPassword);
				
				if(existence) {
					System.out.println("Enter course name to allocate into batch othe course:");
					daoObj.displayCourseAvailableWithOrWithoutSeats("includeslno");
					String cName = sc.next();
					int cId = daoObj.getCourseId(cName);
					
					daoObj.registerBatch(cId, cName, sEmail);
					
				}
				else throw new StudentException("Student "+ daoObj.getSNameFromEmail(sEmail) +" with e-mail "+ sEmail + " not found");
				
			}
			
			
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		} catch (CourseException e) {
			System.out.println(e.getMessage());
		} 
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		
	}

	@Override
	public List<Student> getListOfStudents() {
		
		List<Student> listOfStudents = new ArrayList<>();

		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from students");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				String sEmail = rs.getString("sEmail");
				String sPassword = rs.getString("sPassword");
				String sName = rs.getString("sName");
				Student s = new Student(sEmail, sPassword, sName);
				listOfStudents.add(s);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return listOfStudents;
		
	}

	@Override
	public String getStudentPassword(String sEmail) throws StudentException {
		dao daoObj = new daoImpl();
		String answer = null;
		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select sPassword from students where sEmail = ?");
			ps.setString(1, sEmail);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				answer = rs.getString("sPassword"); 
			}
			else throw new StudentException("Student "+  daoObj.getSNameFromEmail(sEmail) +" with E-mail "+ sEmail +" does not exists");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		
		return answer;
	}

	@Override
	public void adminUpdateTotalSeatsOfaBatch() {

		Scanner sc = new Scanner(System.in);
		
		
		try(Connection conn = DButils.getConnection()){
			dao daoObj = new daoImpl();
			
			List<BatchSeat> listOfBatchSeatDetails = daoObj.getBatchSeatdetails();
			
			if(listOfBatchSeatDetails.size()==0) {
				System.out.println("There are no batches created yet. Please create one to edit it.");
			}
			else {
				System.out.println("Batches available:");
				listOfBatchSeatDetails.forEach(b -> {
					System.out.println(b.getbId());
				});
				System.out.println();
				System.out.println("Enter Batch number:");
				int bId = sc.nextInt();
				System.out.println("Enter new capacity of seats:");
				int newTotalSeats = sc.nextInt();
				PreparedStatement ps = conn.prepareStatement("update batchSeats set totalSeats = ? where bId = ?");
				ps.setInt(1, newTotalSeats);
				ps.setInt(2, bId);
				ps.executeUpdate();
				System.out.println("BatchID "+ bId +" updated to seat capacity "+newTotalSeats);
			}
			
		}	
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void adminViewStudentsOfEveryBatch() {
		
		dao daoObj = new daoImpl();
		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select batchNo, sEmail from batch order by batchNo");
			ResultSet rs = ps.executeQuery();
			boolean isEmpty = true;
			while(rs.next()) {
				if(isEmpty)System.out.println("BatchNo. -> Student name");
				isEmpty = false;

				int batchNo = rs.getInt("batchNo");
				String sEmail = rs.getString("sEmail");
				System.out.println(batchNo +" -> "+ daoObj.getSNameFromEmail(sEmail));
			}
			if(isEmpty)System.out.println("No students available in a batch (or) no batches available. Please check for these conditions.");
			else {
				System.out.println();
				System.out.println("All batches along with their students displayed.");
			}

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<String> getSEmailListFromBatchWhoIsIntoACourse() {
		List<String> SEmailListFromBatchWhoIsIntoACourse = new ArrayList<>();

		try(Connection conn = DButils.getConnection()){

			PreparedStatement ps = conn.prepareStatement("select sEmail from batch");
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				String sEmail = rs.getString("sEmail");
				SEmailListFromBatchWhoIsIntoACourse.add(sEmail);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return SEmailListFromBatchWhoIsIntoACourse;
	}

	@Override
	public String getSNameFromEmail(String sEmail) {
		String name = null;
		
		try(Connection conn = DButils.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select sName from students where sEmail = ?");
			ps.setString(1, sEmail);
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("sName");
			}
			else throw new StudentException("Student with e-mail "+ sEmail+" does not exists");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		} 
		
		return name;
	}
	
	
}



	



