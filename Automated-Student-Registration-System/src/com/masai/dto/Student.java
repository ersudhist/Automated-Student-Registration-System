package com.masai.dto;

import java.util.Objects;

public class Student {

	private String sEmail;
	private String sPassword; 
	private String sName;
	
	public Student(String sEmail, String sPassword, String sName) {
		super();
		this.sEmail = sEmail;
		this.sPassword = sPassword;
		this.sName = sName;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sEmail, sName, sPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(sEmail, other.sEmail) && Objects.equals(sName, other.sName)
				&& Objects.equals(sPassword, other.sPassword);
	}

	@Override
	public String toString() {
		return "Student Email=" + sEmail + ", Student Name=" + sName;
	}
	
	
	
	
}
