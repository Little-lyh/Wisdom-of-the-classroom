package com.pojo;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Students implements Serializable {
    private String username;
    private String password;
    private String name;
    private String address;
    private String phone;
    private int point;
    private Set<P_record> recordsList = new HashSet<P_record>(0);
    private Set<Q_record> q_records = new HashSet<Q_record>(0);

    public Set<Q_record> getQ_records() {
        return q_records;
    }

    public void setQ_records(Set<Q_record> q_records) {
        this.q_records = q_records;
    }

    public Set<P_record> getRecordsList() {
        return recordsList;
    }

    public void setRecordsList(Set<P_record> recordsList) {
        this.recordsList = recordsList;
    }

    public Students() {
    }

    public Students(String username, String password, String name, String address, String phone, int point) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.point = point;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
