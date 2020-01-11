package com.dao;

import org.hibernate.Session;

public class BaseHibernateDao {
    public Session getSession() {
        return HibernateUtil.getSession();
    }
}
