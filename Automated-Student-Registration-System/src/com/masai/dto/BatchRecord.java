package com.masai.dto;

import java.util.Objects;

public class BatchRecord {

	private int batchUid;
	private int batchNo;
	private int cId;
	private String sEmail;
	
	public BatchRecord(int batchUid, int batchNo, int cId, String sEmail) {
		super();
		this.batchUid = batchUid;
		this.batchNo = batchNo;
		this.cId = cId;
		this.sEmail = sEmail;
	}

	public int getBatchUid() {
		return batchUid;
	}

	public void setBatchUid(int batchUid) {
		this.batchUid = batchUid;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(batchNo, batchUid, cId, sEmail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BatchRecord other = (BatchRecord) obj;
		return batchNo == other.batchNo && batchUid == other.batchUid && cId == other.cId
				&& Objects.equals(sEmail, other.sEmail);
	}

	@Override
	public String toString() {
		return "BatchRecordDTO [batchUid=" + batchUid + ", batchNo=" + batchNo + ", cId=" + cId + ", sEmail=" + sEmail
				+ "]";
	}
	
	
	
	
	
}
