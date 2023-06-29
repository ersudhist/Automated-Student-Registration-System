package com.masai.dto;

import java.util.Objects;

public class Course {

	private int cId;
	private String Cname;
	private int fees;
	private String cInfo;
	
	public Course(int cId, String cname, int fees, String cInfo) {
		super();
		this.cId = cId;
		Cname = cname;
		this.fees = fees;
		this.cInfo = cInfo;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public String getcInfo() {
		return cInfo;
	}

	public void setcInfo(String cInfo) {
		this.cInfo = cInfo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Cname, cId, cInfo, fees);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(Cname, other.Cname) && cId == other.cId && Objects.equals(cInfo, other.cInfo)
				&& fees == other.fees;
	}

	@Override
	public String toString() {
		return "Course [cId=" + cId + ", Cname=" + Cname + ", fees=" + fees + ", cInfo=" + cInfo + "]";
	}
	
	
	
}
