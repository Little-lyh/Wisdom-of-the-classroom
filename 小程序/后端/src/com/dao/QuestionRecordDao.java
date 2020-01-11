package com.dao;

import com.pojo.P_record;
import com.pojo.Q_record;
import com.pojo.Question;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QuestionRecordDao {
    public QuestionRecordDao() {
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
    public List<Q_record> getQuestions(){
        getCurrentSession();
        Query query = session.createQuery("from Q_record ");
        List<Q_record> questions=query.list();
        closeSession();
        return questions;
    }
    public List<Q_record> getQuestions(String username){
        getCurrentSession();
        Query query = session.createQuery("from Q_record as r where  r.students.username=:name");
        query.setParameter("name",username);
        List<Q_record> questions=query.list();
        closeSession();
        return questions;
    }
    public boolean insertRecord(Q_record q){
        getCurrentSession();
        t = session.beginTransaction();
        session.save(q);
        t.commit();
        closeSession();
        return true;
    }
}
