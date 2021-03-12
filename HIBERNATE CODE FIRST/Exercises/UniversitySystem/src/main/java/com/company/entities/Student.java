package com.company.entities;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends PersonImpl {

    @Column(name = "average_grade", unique = false, nullable = false)
    @Check(constraints = "average_grade>=2")
    private double averageGrade;

    @Column(name = "attendance")
    private double attendance;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;

    public Student() {
        courses = new HashSet<>();
    }

    public Student(String firstName, String lastName, String phoneNumber, double averageGrade, double attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
        courses = new HashSet<>();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
