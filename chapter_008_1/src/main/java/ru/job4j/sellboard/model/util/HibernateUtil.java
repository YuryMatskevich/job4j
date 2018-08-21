package ru.job4j.sellboard.model.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.Car;
import ru.job4j.sellboard.model.entity.Credential;
import ru.job4j.sellboard.model.entity.User;

/**
 * A class for creating a single <code>SessionFactory</code>
 *
 * @author Yury Matskevich
 */
public class HibernateUtil {
	private static final Logger LOG = Logger.getLogger(HibernateUtil.class);
	//creates a single object of SessionFactory per an application
	private static final SessionFactory FACTORY = buildSessionFactory();

	/**
	 * a private constructor for realization
	 * a pattern of singelton
	 */
	private HibernateUtil() {

	}

	/**
	 * Create the SessionFactory from hibernate.cfg.xml
	 *
	 * @return an instance of <code>SessionFactory</code>
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(Credential.class)
					.addAnnotatedClass(Car.class)
					.addAnnotatedClass(Ad.class)
					.buildSessionFactory();
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Gives a new <code>SessionFactory</code>
	 *
	 * @return a new <code>SessionFactory</code>
	 */
	public static SessionFactory getSessionFactory() {
		return FACTORY;
	}

	/**
	 * Starts a transaction and returns a current session
	 *
	 * @return a current session
	 */
	public static Session beginTransaction() {
		Session hibernateSession = HibernateUtil.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	/**
	 * Commits a transaction
	 */
	public static void commitTransaction() {
		HibernateUtil.getSession().getTransaction().commit();
	}

	/**
	 * Rollbacks a transaction
	 */
	public static void rollbackTransaction() {
		HibernateUtil.getSession().getTransaction().rollback();
	}

	/**
	 * Closes a current session
	 */
	public static void closeSession() {
		HibernateUtil.getSession().close();
	}

	/**
	 * Returns a current session from the <code>SessionFactory</code>
	 *
	 * @return a current session
	 */
	public static Session getSession() {
		return FACTORY.getCurrentSession();
	}
}

