package com.dao;

import com.pojo.Students;
import com.pojo.Teachers;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TeacherDao {
    private Session session;
    private Transaction t;

    public void getCurrentSession() {
        session = HibernateUtil.getSession();
    }

    public void closeSession() {
        if (session != null) {
            HibernateUtil.closeSession();
        }
    }
    public Teachers getTeacher(String username){
        getCurrentSession();
        Teachers teacher=session.get(Teachers.class,username);
        t = session.beginTransaction();
        closeSession();
        return teacher;
    }
}
