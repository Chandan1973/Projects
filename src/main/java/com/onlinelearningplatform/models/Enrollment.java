package com.onlinelearningplatform.models;

import jakarta.persistence.*;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id")  // foreign key reference to Course table
    private Course course;

    private String enrolleddate;
    private String enrolledusername;
    private String enrolleduseremail;
    private int enrolleduserId;
    private int professorId;

    private String enrolledcount;
    private String websiteurl;
    private String coursetype;
    private String language;
    private String description;

    public Enrollment() {
    }

    // ✅ Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getEnrolleddate() {
        return enrolleddate;
    }

    public void setEnrolleddate(String enrolleddate) {
        this.enrolleddate = enrolleddate;
    }

    public String getEnrolledusername() {
        return enrolledusername;
    }

    public void setEnrolledusername(String enrolledusername) {
        this.enrolledusername = enrolledusername;
    }

    public String getEnrolleduseremail() {
        return enrolleduseremail;
    }

    public void setEnrolleduseremail(String enrolleduseremail) {
        this.enrolleduseremail = enrolleduseremail;
    }

    public int getEnrolleduserId() {
        return enrolleduserId;
    }

    public void setEnrolleduserId(int enrolleduserId) {
        this.enrolleduserId = enrolleduserId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getEnrolledcount() {
        return enrolledcount;
    }

    public void setEnrolledcount(String enrolledcount) {
        this.enrolledcount = enrolledcount;
    }

    public String getWebsiteurl() {
        return websiteurl;
    }

    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = websiteurl;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
