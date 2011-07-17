package org.hibernate.tutorial.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Helper class that takes care of startup and makes accessing the org.hibernate.SessionFactory more
 * convenient
 * 
 * @author stivlo
 * 
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Logger log = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            log.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
