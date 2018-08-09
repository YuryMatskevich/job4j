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

	private <T> void txVoid(final Function<Session, T> command) {
		Transaction transact = null;
		try (Session session = ConnectionHb.getSession()) {
			transact = session.beginTransaction();
			command.apply(session);
			transact.commit();
		} catch (Exception e) {
			if (transact != null) {
				transact.rollback();
			}
			throw e;
		}
	}

	private <T> T txT(final Function<Session, T> command) {
		Transaction transact = null;
		T items;
		try (Session session = ConnectionHb.getSession()) {
			transact = session.beginTransaction();
			items = command.apply(session);
			transact.commit();
		} catch (Exception e) {
			if (transact != null) {
				transact.rollback();
			}
			throw e;
		}
		return items;
	}

	/**
	 * Adds a new item to a db
	 *
	 * @param item an item for adding to a Db
	 */
	public void createItem(Item item) {
		txVoid(session -> session.save(item));
	}

	/**
	 * Finds all the items which are in a db
	 *
	 * @return <code>List</code> of {@link Item}
	 * from a db
	 */
	@SuppressWarnings("unchecked")
	public List<Item> listItem() {
		return txT(
				session -> (List<Item>) session.createQuery("from Item").list()
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
		return txT(
				session -> {
					Query query = session.createQuery(
							"from Item where done =: paramDone");
					query.setParameter("paramDone", false);
					return query.list();
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
		txVoid(session -> {
					Query query = session.createQuery(
							"update Item set done =: paramDone where id =: paramId");
					query.setParameter("paramDone", done);
					query.setParameter("paramId", id);
					return query.executeUpdate();
				}
		);
	}
}
