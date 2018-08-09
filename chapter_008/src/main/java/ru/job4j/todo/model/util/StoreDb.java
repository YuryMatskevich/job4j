package ru.job4j.todo.model.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.todo.model.entity.Item;

import java.util.List;

/**
 * A class allows some method fore working with a db
 *
 * @author Yury Matskevich
 */
public class StoreDb {
	private static StoreDb uniqueInstance = new StoreDb();

	private StoreDb() {

	}

	public static StoreDb getInstance() {
		return uniqueInstance;
	}

	/**
	 * Adds a new item to a db
	 *
	 * @param item an item for adding to a Db
	 */
	public void createItem(Item item) {
		Transaction transact = null;
		try (Session session = ConnectionHb.getSession()) {
			transact = session.beginTransaction();
			session.save(item);
			transact.commit();
		} catch (HibernateException e) {
			if (transact != null) {
				transact.rollback();
			}
		}
	}

	/**
	 * Finds all the items which are in a db
	 *
	 * @return <code>List</code> of {@link Item}
	 * from a db
	 */
	@SuppressWarnings("unchecked")
	public List<Item> listItem() {
		List<Item> items = null;
		Transaction transact = null;
		try (Session session = ConnectionHb.getSession()) {
			transact = session.beginTransaction();
			items = (List<Item>) session.createQuery("from Item").list();
			transact.commit();
		} catch (HibernateException e) {
			if (transact != null) {
				transact.rollback();
			}
		}
		return items;
	}

	/**
	 * Finds all the items which are in a db
	 *
	 * @return <code>List</code> of {@link Item}
	 * from a db
	 */
	@SuppressWarnings("unchecked")
	public List<Item> listNoteDoneItem() {
		List<Item> items = null;
		Transaction transact = null;
		try (Session session = ConnectionHb.getSession()) {
			transact = session.beginTransaction();
			Query query = session.createQuery("from Item where done =: paramDone");
			query.setParameter("paramDone", false);
			items = query.list();
			transact.commit();
		} catch (HibernateException e) {
			if (transact != null) {
				transact.rollback();
			}
		}
		return items;
	}

	/**
	 * Updates a state of a current item
	 *
	 * @param id   an id of a current item
	 * @param done a state of a item for updating
	 */
	public void updateDone(int id, boolean done) {
		Transaction transact = null;
		try (Session session = ConnectionHb.getSession()) {
			transact = session.beginTransaction();
			Query query = session.createQuery("update Item set done =: paramDone where id =: paramId");
			query.setParameter("paramDone", done);
			query.setParameter("paramId", id);
			query.executeUpdate();
			transact.commit();
		} catch (HibernateException e) {
			if (transact != null) {
				transact.rollback();
			}
		}
	}
}
