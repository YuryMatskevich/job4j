package ru.job4j.todo.model.util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.todo.model.entity.Item;

import java.util.List;
import java.util.function.Function;

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

	private <T> T tx(final Function<Session, T> command) {
		Session session = ConnectionHb.getSession();
		Transaction transact = session.beginTransaction();
		try {
			return command.apply(session);
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			transact.commit();
			session.close();
		}
	}

	/**
	 * Adds a new item to a db
	 *
	 * @param item an item for adding to a Db
	 */
	public void createItem(Item item) {
		tx(session -> session.save(item));
	}

	/**
	 * Finds all the items which are in a db
	 *
	 * @return <code>List</code> of {@link Item}
	 * from a db
	 */
	@SuppressWarnings("unchecked")
	public List<Item> listItem() {
		return tx(
				session -> (List<Item>) session.createQuery("from Item").getResultList()
		);
	}

	/**
	 * Finds all the items which are in a db
	 *
	 * @return <code>List</code> of {@link Item}
	 * from a db
	 */
	@SuppressWarnings("unchecked")
	public List<Item> listNoteDoneItem() {
		return tx(
				session -> {
					Query query = session.createQuery(
							"from Item where done =: paramDone"
					);
					query.setParameter("paramDone", false);
					return query.getResultList();
				}
		);
	}

	/**
	 * Updates a state of a current item
	 *
	 * @param id   an id of a current item
	 * @param done a state of a item for updating
	 */
	public void updateDone(int id, boolean done) {
		tx(session -> {
					Query query = session.createQuery(
							"update Item set done =: paramDone where id =: paramId"
					);
					query.setParameter("paramDone", done);
					query.setParameter("paramId", id);
					return query.executeUpdate();
				}
		);
	}
}
