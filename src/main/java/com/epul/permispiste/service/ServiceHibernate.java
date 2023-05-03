package com.epul.permispiste.service;

import com.epul.permispiste.mesExceptions.MonException;
import com.epul.permispiste.mesExceptions.ServiceHibernateException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ServiceHibernate {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // on lit la configuration du fichier hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
            // Le fichier est lu
        } catch (HibernateException ex) {
            throw new MonException("Impossible d'accéder à la SessionFactory: ", ex.getMessage());
        }
    }

    public static final ThreadLocal session = new ThreadLocal();

    public static Session currentSession() throws MonException {
        Session s = null;
        try {
            s = (Session) session.get();
            // Open a new Session, if this Thread has none yet
            if (s == null || !s.isOpen()) {
                s = sessionFactory.openSession();
                session.set(s);
            }
        } catch (HibernateException ex) {
            throw new MonException("Impossible d'accéder à la SessionFactory: ", ex.getMessage());
        }
        return s;
    }

    @SuppressWarnings("unchecked")
    public static void closeSession() throws ServiceHibernateException {
        try {
            Session s = (Session) session.get();
            session.set(null);
            if (s != null)
                s.close();
        } catch (HibernateException ex) {
            throw new MonException("Impossible d'accéder à la SessionFactory: ", ex.getMessage());
        }
    }
}
