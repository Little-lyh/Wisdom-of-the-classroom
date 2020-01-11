package com.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class HibernateUtil {
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
    private static final ThreadLocal<Session> threadLoacl = new ThreadLocal<Session>();
    private static Configuration configuration = new Configuration();
    private static org.hibernate.SessionFactory sessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;
    static {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }
    HibernateUtil() {
    }

    public static org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void rebuildSessionFactory() {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }

    public static void setConfigFile(String configFile) {
        HibernateUtil.configFile = configFile;
        sessionFactory = null;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLoacl.get();
        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null)
                    ? sessionFactory.openSession() : null;
            threadLoacl.set(session);
        }
        return session;
    }

    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLoacl.get();
        threadLoacl.set(null);
        if (session != null) {
            session.close();
        }
    }
    public static List search(String hql){
        Session session=null;
        session=getSession();
        List aList=null;
        aList=session.createQuery(hql).list();
        session.close();
        return aList;
    }
}
