package crudapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ConnectionHibernate {
    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    private static ConnectionHibernate session;

    private ConnectionHibernate(){}

    public static ConnectionHibernate getConnectSession(){
        if(session == null){
            session = new ConnectionHibernate();
        }
        return session;
    }

    public SessionFactory getSession(){
        return sessionFactory;
    }
}
