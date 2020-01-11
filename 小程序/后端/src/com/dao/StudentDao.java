package com.dao;


import com.pojo.P_record;
import com.pojo.Q_record;
import com.pojo.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class StudentDao extends BaseHibernateDao {
    public StudentDao() {
        System.out.println("create StudentDao");
    }

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

    public boolean login(Students students) {
        getCurrentSession();
        t = session.beginTransaction();
        String username = students.getUsername();
        String password = students.getPassword();
        Query query = session.createQuery(String.format("from StudentDao where username='%s' and password='%s'", username, password));
        List list = query.list();
        t.commit();
        closeSession();
        return list != null;
    }
    public Students getStudent(String username){
        getCurrentSession();
        Students student=session.get(Students.class,username);
        closeSession();
        return student;
    }
    public boolean changePoint(String username,int new_point){
        getCurrentSession();
        t = session.beginTransaction();
        Students students=session.get(Students.class,username);
        int old=students.getPoint();
        students.setPoint(old+new_point);
        session.save(students);
        t.commit();
        closeSession();
        return true;
    }
    public boolean changePoint1(String username,int new_point){
        getCurrentSession();
        boolean success=true;
        t = session.beginTransaction();
        Students students=session.get(Students.class,username);
        int old=students.getPoint();
        if(old<new_point){
            success=false;
        }else{
            students.setPoint(old-new_point);
            session.save(students);
        }
        t.commit();
        closeSession();
        return success;
    }
    public List<Students> getStudentRank(){
        getCurrentSession();
        Query query = session.createQuery("from Students order by point desc ");
        List<Students> questions=query.list();
        closeSession();
        return questions;
    }


}
