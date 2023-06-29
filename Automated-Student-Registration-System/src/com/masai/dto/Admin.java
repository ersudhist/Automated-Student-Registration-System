package com.masai.dto;

import java.util.Objects;

public class Admin {

	private String name;
	private String password;
	
	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}
	

	@Override
	public String toString() {
		return "Admin name=" + name + ", password=" + password;
	}
	
	
}
