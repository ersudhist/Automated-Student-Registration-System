package com.masai;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EducationalInstitute {
	

    private Map<String, Course> courses = new HashMap<>();
    private Map<String, List<Batch>> batchesByCourse = new HashMap<>();

    public void addCourse(String name, int durationInWeeks, double fee) {
        if (courses.containsKey(name)) {
            throw new IllegalArgumentException("Course with the same name already exists");
        }

        courses.put(name, new Course(name, durationInWeeks, fee));
    }

    public List<Course> searchCourses(String name, int minDuration, int maxDuration, double minFee, double maxFee) {
        List<Course> matchingCourses = new ArrayList<>();

        for (Course course : courses.values()) {
            if (name == null || course.getName().contains(name)) {
                if (course.getDurationInWeeks() >= minDuration && course.getDurationInWeeks() <= maxDuration) {
                    if (course.getFee() >= minFee && course.getFee() <= maxFee) {
                        matchingCourses.add(course);
                    }
                }
            }
        }

        return matchingCourses;
    }

    public void updateCourse(String name, double fee) {
        Course course = courses.get(name);

        if (course == null) {
            throw new IllegalArgumentException("Course with the given name does not exist");
        }

        course.setFee(fee);
    }

    public void createBatch(String courseName, String batchName, LocalDate startDate, LocalDate endDate, int maxStudents) {
        Course course = courses.get(courseName);

        if (course == null) {
            throw new IllegalArgumentException("Course with the given name does not exist");
        }

        List<Batch> batches = batchesByCourse.computeIfAbsent(courseName, k -> new ArrayList<>());

        if (batches.stream().anyMatch(b -> b.getName().equals(batchName))) {
            throw new IllegalArgumentException("Batch with the same name already exists for the course");
        }

        batches.add(new Batch(batchName, course, startDate, endDate, maxStudents));
    }


}
