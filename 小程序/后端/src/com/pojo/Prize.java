package com.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prize {
    private int id;
    private String name;
    private String detail;
    private String picsrc;
    private int point;
    private Set<P_record> recordList = new HashSet<P_record>(0);

    public Set<P_record> getRecordList() {
        return recordList;
    }

    public void setRecordList(Set<P_record> recordList) {
        this.recordList = recordList;
    }
    public Prize(){}
    public Prize(int id, String name, String detail, String picsrc, int point) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.picsrc = picsrc;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPicsrc() {
        return picsrc;
    }

    public void setPicsrc(String picsrc) {
        this.picsrc = picsrc;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}

