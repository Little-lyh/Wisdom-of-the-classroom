package com.dao;

import com.pojo.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManagerDao {
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
    public Manager getManager(String username){
        getCurrentSession();
        Manager manager=session.get(Manager.class,username);
        closeSession();
        return manager;
    }
}
