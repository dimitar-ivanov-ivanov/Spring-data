package com.company.entities;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends PersonImpl {

    @Column(name = "email", length = 40, unique = true)
    private String email;

    @Column(name = "salary_per_hour", nullable = false)
    @Check(constraints = "salary_per_hour>=0")
    private double salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;

    public Teacher() {
        courses = new HashSet<>();
    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, double salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        courses = new HashSet<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
