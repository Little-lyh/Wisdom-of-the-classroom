package com.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Question implements Serializable {
    private int id;
    private String question;
    private String option_A;
    private String option_B;
    private String option_C;
    private String option_D;
    private String answer;
    private String point;
    private Teachers teachers;
    private Set<Q_record> q_records = new HashSet<Q_record>();
    public Set<Q_record> getQ_records() {
        return q_records;
    }

    public void setQ_records(Set<Q_record> q_records) {
        this.q_records = q_records;
    }
    public Question(){}
    public Question(int id, String question, String option_A, String option_B, String option_C, String option_D, String answer, String point, Teachers teachers) {
        this.id = id;
        this.question = question;
        this.option_A = option_A;
        this.option_B = option_B;
        this.option_C = option_C;
        this.option_D = option_D;
        this.answer = answer;
        this.point = point;
        this.teachers = teachers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption_A() {
        return option_A;
    }

    public void setOption_A(String option_A) {
        this.option_A = option_A;
    }

    public String getOption_B() {
        return option_B;
    }

    public void setOption_B(String option_B) {
        this.option_B = option_B;
    }

    public String getOption_C() {
        return option_C;
    }

    public void setOption_C(String option_C) {
        this.option_C = option_C;
    }

    public String getOption_D() {
        return option_D;
    }

    public void setOption_D(String option_D) {
        this.option_D = option_D;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }
}
