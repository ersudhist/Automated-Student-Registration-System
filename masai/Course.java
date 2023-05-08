package com.masai;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Course {
    private String name;
    private int durationInWeeks;
    private double fee;
	private String name2;
	private int duration;
	private int fee2;

    public Course(String name, int durationInWeeks, double fee) {
        this.name = name;
        this.durationInWeeks = durationInWeeks;
        this.fee = fee;
    }

    public Course(String name2, int duration, int fee2) {
    	 this.name2 = name;
         this.duration = duration;
         this.fee2 = fee2;
	}

	public String getName() {
        return name;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}




