package org.hibernate.tutorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;

public class HibernatConsole {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// #log4jdbc driver
		// #jdbc.driver=net.sf.log4jdbc.DriverSpy
		// #jdbc.url=jdbc:log4jdbc:h2:file:~/.h2/quickstart;AUTO_SERVER=TRUE

		// <property
		// name="connection.driver_class">org.hsqldb.jdbcDriver</property>
		// <property
		// name="connection.url">jdbc:hsqldb:hsql://localhost</property>
		// <property name="connection.username">sa</property>
		// <property name="connection.password"></property>
		//
		// <!-- JDBC connection pool (use the built-in) -->
		// <property name="connection.pool_size">3</property>
		//
		// <!-- SQL dialect -->
		// <property name="dialect">org.hibernate.dialect.HSQLDialect</property>
		// <property name="show_sql">true</property>

		// <!-- Drop and re-create the database schema on startup -->
		// <property name="hbm2ddl.auto">update</property>

		Class.forName("org.h2.Driver");
		SessionFactory buildSessionFactory = new Configuration().setProperty("hibernate.connection.driver_class", "org.h2.Driver")
				.setProperty("hibernate.connection.url", "jdbc:h2:file:~/.h2/quickstart;AUTO_SERVER=TRUE")
				.setProperty("hibernate.connection.username", "sa").setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect").configure().buildSessionFactory();
		Session session = buildSessionFactory.getCurrentSession();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String readLine = null;

		Person p = new Person();
		p.setAge(22);
		HashSet<String> emailAddresses = new HashSet<String>();
		emailAddresses.add("rockflyrain@gmail.com");
		emailAddresses.add("thinkdeeplyjh@163.com");
		p.setEmailAddresses(emailAddresses);
		p.setFirstname("Bruce");
		p.setLastname("zhang");
		Set<Event> events = new HashSet<Event>();
		Event e = new Event();
		e.setTitle("eat");
		e.setDate(new Date());
		events.add(e);

		Event event = new Event();
		event.setDate(new Date());
		event.setTitle("sleep");
		events.add(event);
		p.setEvents(events);

		// session.beginTransaction();
		// session.save(e);
		// session.save(event);
		// session.save(p);
		// session.getTransaction().commit();

		while ((readLine = reader.readLine()) != null) {
			session = buildSessionFactory.getCurrentSession();
			session.beginTransaction();
			System.out.println(readLine);
			try {
				Query query = session.createQuery(readLine);
				List list = query.list();
				System.out.println(list);
			} catch (HibernateException e1) {
				System.out.println(e1.getMessage());
			}
			session.getTransaction().commit();
		}
		// Person aPerson = (Person) session.load(Person.class, personId);
		// adding the emailAddress collection might trigger a lazy load of the
		// collection
		// aPerson.getEmailAddresses().add(emailAddress);

	}

}
