package com.pojo;
import com.pojo.Prize;
import java.io.Serializable;
import java.util.Date;

public class P_record implements Serializable {
    private int id;
    private Prize prize;
    private com.pojo.Students students;
    private Date date;
    public P_record() {}

    public P_record(int id, Prize prize, Students students, Date date) {
        this.id = id;
        this.prize = prize;
        this.students = students;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
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
}
