package com.masai.dto;

import java.util.Objects;

public class BatchSeat {

	private int bId;
	private int cId;
	private int totalSeats;
	private int seatsFilled;
	
	public BatchSeat(int bId, int cId, int totalSeats, int seatsFilled) {
		super();
		this.bId = bId;
		this.cId = cId;
		this.totalSeats = totalSeats;
		this.seatsFilled = seatsFilled;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getSeatsFilled() {
		return seatsFilled;
	}

	public void setSeatsFilled(int seatsFilled) {
		this.seatsFilled = seatsFilled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bId, cId, seatsFilled, totalSeats);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BatchSeat other = (BatchSeat) obj;
		return bId == other.bId && cId == other.cId && seatsFilled == other.seatsFilled
				&& totalSeats == other.totalSeats;
	}

	@Override
	public String toString() {
		return "batchSeatDTO [bId=" + bId + ", cId=" + cId + ", totalSeats=" + totalSeats + ", seatsFilled="
				+ seatsFilled + "]";
	}
	
	
	
	
	

	
}
