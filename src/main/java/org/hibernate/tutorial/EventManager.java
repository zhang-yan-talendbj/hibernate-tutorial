package org.hibernate.tutorial;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventManager mgr = new EventManager();
        if (args.length != 1) {
            System.err.println("Please add the 'store' argument to create a new event");
            return;
        }
        if (args[0].equals("store")) {
            mgr.createAndStoreNewEvent("My Event", new Date());
        }

    }

    private void createAndStoreNewEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);
        session.getTransaction().commit();
    }

}
