package com.masai;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Batch {
	

    private String name;
    private Course course;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxStudents;
    private List<String> students = new ArrayList<>();

    public Batch(String name, Course course, LocalDate startDate, LocalDate endDate, int maxStudents) {
        this.name = name;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxStudents = maxStudents;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public List<String> getStudents() {
        return students;
    }

    public boolean isFull() {
        return students.size() == maxStudents;
    }


}
