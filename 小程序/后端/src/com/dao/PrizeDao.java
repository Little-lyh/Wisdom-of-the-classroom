package com.dao;

import com.pojo.Prize;
import com.pojo.Question;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PrizeDao {
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

    public List<Prize> getPrizeList(){
        getCurrentSession();
        Query query = session.createQuery("from Prize");
        List<Prize> prizes=query.list();
        closeSession();
        return prizes;
    }
    public Prize getPrize(int id){
        getCurrentSession();
        Prize prize = session.get(Prize.class,id);
        closeSession();
        return prize;
    }
}
