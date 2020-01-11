package com.dao;

import com.pojo.P_record;
import com.pojo.Q_record;
import com.pojo.Question;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PrizeRecordDao {
    public PrizeRecordDao() {
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
    public List<P_record> getQuestions(){
        getCurrentSession();
        Query query = session.createQuery("from P_record");
        List<P_record> questions=query.list();
        closeSession();
        return questions;
    }
    public List<P_record> getQuestions(String username){
        getCurrentSession();
        Query query = session.createQuery("select r from P_record as r where  r.students.username=:name");
        query.setParameter("name",username);
        List<P_record> questions=query.list();
        closeSession();
        return questions;
    }
    public boolean insertRecord(P_record p){
        getCurrentSession();
        t = session.beginTransaction();
        session.save(p);
        t.commit();
        closeSession();
        return true;
    }
}
