package com.onlinelearningplatform.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String coursename;
    private String courseid;
    private String enrolleddate;
    private int professorId;

    private String instructorname;
    private String instructorinstitution;
    private String enrolledcount;

    private String imageurl;
    private String websiteurl;
    private String coursetype;
    private String skilllevel;
    private String language;
    private String description;
    private String youtubeurl;

    // ✅ Questions mapped with course
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Question> questions;

    // ✅ Enrollments mapped with course
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments;

    // 🔹 Constructors
    public Course() {}

    public Course(int id, String coursename, String courseid, String enrolleddate, String instructorname,
                  String instructorinstitution, String enrolledcount, String youtubeurl, String websiteurl,
                  String coursetype, String skilllevel, String language, String description, String imageurl) {
        this.id = id;
        this.coursename = coursename;
        this.courseid = courseid;
        this.enrolleddate = enrolleddate;
        this.instructorname = instructorname;
        this.instructorinstitution = instructorinstitution;
        this.enrolledcount = enrolledcount;
        this.imageurl = imageurl;
        this.youtubeurl = youtubeurl;
        this.websiteurl = websiteurl;
        this.coursetype = coursetype;
        this.skilllevel = skilllevel;
        this.language = language;
        this.description = description;
    }

    // 🔹 Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCoursename() { return coursename; }
    public void setCoursename(String coursename) { this.coursename = coursename; }

    public String getCourseid() { return courseid; }
    public void setCourseid(String courseid) { this.courseid = courseid; }

    public String getEnrolleddate() { return enrolleddate; }
    public void setEnrolleddate(String enrolleddate) { this.enrolleddate = enrolleddate; }

    public int getProfessorId() { return professorId; }
    public void setProfessorId(int professorId) { this.professorId = professorId; }

    public String getInstructorname() { return instructorname; }
    public void setInstructorname(String instructorname) { this.instructorname = instructorname; }

    public String getInstructorinstitution() { return instructorinstitution; }
    public void setInstructorinstitution(String instructorinstitution) { this.instructorinstitution = instructorinstitution; }

    public String getEnrolledcount() { return enrolledcount; }
    public void setEnrolledcount(String enrolledcount) { this.enrolledcount = enrolledcount; }

    public String getImageurl() { return imageurl; }
    public void setImageurl(String imageurl) { this.imageurl = imageurl; }

    public String getWebsiteurl() { return websiteurl; }
    public void setWebsiteurl(String websiteurl) { this.websiteurl = websiteurl; }

    public String getCoursetype() { return coursetype; }
    public void setCoursetype(String coursetype) { this.coursetype = coursetype; }

    public String getSkilllevel() { return skilllevel; }
    public void setSkilllevel(String skilllevel) { this.skilllevel = skilllevel; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getYoutubeurl() { return youtubeurl; }
    public void setYoutubeurl(String youtubeurl) { this.youtubeurl = youtubeurl; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public List<Enrollment> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrollment> enrollments) { this.enrollments = enrollments; }
}
