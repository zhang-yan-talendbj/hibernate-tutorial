package org.hibernate.tutorial.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.StaleObjectStateException;
import org.hibernate.classic.Session;
import org.hibernate.tutorial.util.HibernateUtil;
import org.junit.Test;

public class EventTest {

	@Test
	public void testOptimitLock() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Session s2 = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			s2.beginTransaction();
			Query query = session.createQuery("from Event where title='bruce'");
			query.setLockMode("Event", LockMode.UPGRADE_NOWAIT);
			List list = query.list();
			Event eee = (Event) list.get(0);
			eee.setDate(new Date());
			System.out.println(list);
			assertEquals("", "");
			Query query1 = s2.createQuery("from Event where title='bruce'");
			query1.setLockMode("Event", LockMode.UPGRADE_NOWAIT);
			List list1 = query1.list();
			System.out.println(list1);
			Event ee = (Event) list1.get(0);
			ee.setDate(new Date());
			assertEquals("", "");
			s2.getTransaction().commit();
			session.getTransaction().commit();
			fail();
		} catch (StaleObjectStateException e) {
			assertEquals("Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect): [org.hibernate.tutorial.domain.Event#1]", e.getMessage());
		}
	}

	private Event getEvent() {
		Event e = new Event();
		e.setDate(new Date());
		e.setTitle("title");
		return e;
	}

}
