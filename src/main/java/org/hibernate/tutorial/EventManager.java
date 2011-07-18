package org.hibernate.tutorial;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
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
        } else if (args[0].equals("list")) {
            List<Event> events = mgr.listEvents();
            for (int i = 0; i < events.size(); i++) {
                Event theEvent = events.get(i);
                System.out.println("Event: " + theEvent.getTitle() + " Time: " + theEvent.getDate());
            }
        } else if (args[0].equals("addpersontoevent")) {
            Long eventId = mgr.createAndStoreNewEvent("My Event", new Date());
            Long personId = mgr.createAndStoreNewPerson("First Name", "Last Name", 26);
            mgr.addPersonToEvent(personId, eventId);
            System.out.println("Added person " + personId + " to event " + eventId);
        }

    }

    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Event> result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }

    /**
     * Create a new event and store it in the DB
     * 
     * @param title
     *            event title
     * @param theDate
     *            event date
     * @return the generated id
     */
    private Long createAndStoreNewEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        Long id = (Long) session.save(theEvent);
        session.getTransaction().commit();
        return id;
    }

    /**
     * Create a new person and store it in the DB
     * 
     * @param firstname
     *            first name of the person
     * @param lastname
     *            last name of the person
     * @param age
     *            age at the moment of person creation
     * @return the generated id
     */
    private Long createAndStoreNewPerson(String firstname, String lastname, int age) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person thePerson = new Person();
        thePerson.setFirstname(firstname);
        thePerson.setLastname(lastname);
        thePerson.setAge(age);
        Long id = (Long) session.save(thePerson);
        session.getTransaction().commit();
        return id;
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);
        session.getTransaction().commit();
    }

}
