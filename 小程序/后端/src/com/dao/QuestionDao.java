package com.dao;

import com.pojo.Question;
import com.pojo.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QuestionDao {
    public QuestionDao() {
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
    public List<Question> getQuestions(){
        getCurrentSession();
        Query query = session.createQuery("from Question");
        List<Question> questions=query.list();
        closeSession();
        return questions;
    }
    public List<Question> getQuestions(String username){
        getCurrentSession();
        Query query = session.createQuery("select q from Question as q where q.teachers.username=:name");
        query.setParameter("name",username);
        List<Question> questions=query.list();
        closeSession();
        return questions;
    }
    public Question getQuestion(int id){
        getCurrentSession();
        Question question=session.get(Question.class,id);
        closeSession();
        return question;
    }
    public boolean addQuestion(Question question){
        getCurrentSession();
        t = session.beginTransaction();
        session.save(question);
        t.commit();
        closeSession();
        return true;
    }
    public boolean changeQuestion(Question question){
        getCurrentSession();
        t = session.beginTransaction();
        session.update(question);
        t.commit();
        closeSession();
        return true;
    }
    public boolean delQuestion(Question question){
        getCurrentSession();
        t = session.beginTransaction();
        session.delete(question);
        t.commit();
        closeSession();
        return true;
    }

}
