package com.pojo;

import java.io.Serializable;
import java.util.Date;

public class Q_record implements Serializable {
    private int id;
    private Question question;
    private Students students;
    private Date date;
    private String state;

   public Q_record() {
        this.state = "错";
    }

    public Q_record(int id, Question question, Students students, Date date, String state) {
        this.id = id;
        this.question = question;
        this.students = students;
        this.date = date;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Date getDate() {
        return new java.util.Date(date.getTime());
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
