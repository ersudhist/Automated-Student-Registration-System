package com.masai;

import java.util.*;

public class Admin {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    private List<Course> courses;
    private List<Student> students;

    public Admin() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }

    public void addCourse(String name, int duration, int fee) {
        Course course = new Course(name, duration, fee);
        courses.add(course);
        System.out.println("Course added successfully: " + course);
    }

    public List<Course> searchCourses(String name, int minDuration, int maxDuration, int minFee, int maxFee) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (name != null && !name.isEmpty() && !course.getName().toLowerCase().contains(name.toLowerCase())) {
                continue;
            }
            if (course.getDuration() < minDuration || course.getDuration() > maxDuration) {
                continue;
            }
            if (course.getFee() < minFee || course.getFee() > maxFee) {
                continue;
            }
            result.add(course);
        }
        return result;
    }

    public Course findCourseByName(String name) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(name)) {
                return course;
            }
        }
        return null;
    }

    public void updateCourse(Course course, String newName, int newDuration, int newFee) {
        course.setName(newName);
        course.setDuration(newDuration);
        course.setFee(newFee);
        System.out.println("Course updated successfully: " + course);
    }

    public void createBatch(Course course, String name, Date startDate, Date endDate, int capacity) {
        Batch batch = new Batch(name, startDate, endDate, capacity, course);
        course.addBatch(batch);
        System.out.println("Batch created successfully: " + batch);
    }

    public List<Batch> searchBatches(String name, Date minStartDate, Date maxStartDate, Date minEndDate, Date maxEndDate, Course course) {
        List<Batch> result = new ArrayList<>();
        for (Course c : courses) {
            if (course != null && !c.equals(course)) {
                continue;
            }
            for (Batch batch : c.getBatches()) {
                if (name != null && !name.isEmpty() && !batch.getName().toLowerCase().contains(name.toLowerCase())) {
                    continue;
                }
                if (batch.getStartDate().before(minStartDate) || batch.getStartDate().after(maxStartDate)) {
                    continue;
                }
                if (batch.getEndDate().before(minEndDate) || batch.getEndDate().after(maxEndDate)) {
                    continue;
                }
                result.add(batch);
            }
        }
        return result;
    }

    public void updateBatch(Batch batch, String newName, Date newStartDate, Date newEndDate, int newCapacity) {
        batch.setName(newName);
        batch.setStartDate(newStartDate);
        batch.setEndDate(newEndDate);
        batch.setCapacity(newCapacity);
        System.out.println("Batch updated successfully: " + batch);
    }

    public void viewStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

    
