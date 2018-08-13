package ru.job4j.todo.model.util;

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
		final Session session = ConnectionHb.getSession();
		final Transaction transact = session.beginTransaction();
		try {
			session.save(item);
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			transact.commit();
			session.close();
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
		final Session session = ConnectionHb.getSession();
		final Transaction transact = session.beginTransaction();
		try {
			return (List<Item>) session.createQuery("from Item").getResultList();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			transact.commit();
			session.close();
		}
	}

	/**
	 * Finds all the items which are in a db
	 *
	 * @return <code>List</code> of {@link Item}
	 * from a db
	 */
	@SuppressWarnings("unchecked")
	public List<Item> listNoteDoneItem() {
		final Session session = ConnectionHb.getSession();
		final Transaction transact = session.beginTransaction();
		try {
			Query query = session.createQuery("from Item where done =: paramDone");
			query.setParameter("paramDone", false);
			return query.list();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			transact.commit();
			session.close();
		}
	}

	/**
	 * Updates a state of a current item
	 *
	 * @param id   an id of a current item
	 * @param done a state of a item for updating
	 */
	public void updateDone(int id, boolean done) {
		final Session session = ConnectionHb.getSession();
		final Transaction transact = session.beginTransaction();
		try {
			Query query = session.createQuery("update Item set done =: paramDone where id =: paramId");
			query.setParameter("paramDone", done);
			query.setParameter("paramId", id);
			query.executeUpdate();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			transact.commit();
			session.close();
		}
	}
}
