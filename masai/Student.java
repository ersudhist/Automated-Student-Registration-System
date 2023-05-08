package com.masai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastName;
    private String address;
    private String mobileNumber;
    private String email;
    private String password;
    private List<Course> coursesEnrolled;

    public Student(String firstName, String lastName, String address, String mobileNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.coursesEnrolled = new ArrayList<>();
    }

    public void updatePersonalDetails(String firstName, String lastName, String address, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    public void changePassword(String oldPassword, String newPassword) throws Exception {
        if (!this.password.equals(oldPassword)) {
            throw new Exception("Incorrect old password");
        }
        this.password = newPassword;
    }

    public List<Course> getAllCourses() {
        return Institute.getAllCourses();
    }

    public Map<Course, List<Batch>> getAllBatches() {
        Map<Course, List<Batch>> courseBatchMap = new HashMap<>();
        for (Course course : Institute.getAllCourses()) {
            List<Batch> batches = course.getBatches();
            if (!batches.isEmpty()) {
                courseBatchMap.put(course, batches);
            }
        }
        return courseBatchMap;
    }

    public void enrollCourse(String courseName, String batchName) throws Exception {
        Course course = Institute.getCourseByName(courseName);
        if (course == null) {
            throw new Exception("Invalid course name");
        }
        Batch batch = course.getBatchByName(batchName);
        if (batch == null) {
            throw new Exception("Invalid batch name");
        }
        if (batch.isFull()) {
            throw new Exception("Batch is full");
        }
        if (coursesEnrolled.contains(course)) {
            throw new Exception("Already enrolled in this course");
        }
        coursesEnrolled.add(course);
        batch.addStudent(this);
    }

    public void viewEnrolledCourses() {
        for (Course course : coursesEnrolled) {
            System.out.println(course.getName());
        }
    }
}

