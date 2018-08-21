package ru.job4j.todo.model.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * A class for creating a single <code>SessionFactory</code>
 * and creating <code>Session</code>
 * @author Yury Matskevich
 */
class ConnectionHb {
	//creates a single object of SessionFactory per an application
	private static final SessionFactory FACTORY =
			new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	/**
	 * a private constructor for realization
	 * a pattern of singelton
	 */
	private ConnectionHb() {

	}

	/**
	 * Creates a new <code>Session</code>
	 * @return a new <code>Session</code>
	 */
	static Session getSession() {
		return FACTORY.openSession();
	}
}
