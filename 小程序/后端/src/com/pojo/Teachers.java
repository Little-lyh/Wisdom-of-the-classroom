package com.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
public class Teachers implements Serializable {
    private String username;
    private String password;
    private String name;
    private Set<P_record> questionList = new HashSet<P_record>(0);
    public Teachers(){}

    public Teachers(String username, String password, String name, Set<P_record> questionList) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.questionList = questionList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<P_record> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(Set<P_record> questionList) {
        this.questionList = questionList;
    }
}
