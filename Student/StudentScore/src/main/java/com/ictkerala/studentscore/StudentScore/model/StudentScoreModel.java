package com.ictkerala.studentscore.StudentScore.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentScoreModel {


    @Id
    @JsonProperty("id")
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("score")
    private  float score;

    public StudentScoreModel(String  studId, String name, String subject, float score) {
        this.studId = studId;
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    public StudentScoreModel() {

    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
