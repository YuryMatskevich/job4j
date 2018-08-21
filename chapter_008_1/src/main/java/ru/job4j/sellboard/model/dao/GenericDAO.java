package ru.job4j.sellboard.model.dao;

import java.io.Serializable;

/**
 * This generic interface determines CRUDE opeations for some entity
 *
 * @author Yury Matskevich
 */
public interface GenericDAO<T, PK extends Serializable> {
	/**
	 * Creates a new instance in a db
	 * @param newInstance a new instance of <code>T</code>
	 * @return a new generated primary key
	 */
	PK create(T newInstance);

	/**
	 * Gives an instance from a db, using an unique id like a primary key
	 * @param id a primary key
	 * @return an instance from a db
	 */
	T read(Class type, PK id);

	/**
	 * Updates an instance which is existed in a db
	 * @param transientObject an instance for update
	 */
	void update(T transientObject);

	/**
	 * Deletes current object from a db
	 * @param persistentObject an instance for delete
	 */
	void delete(T persistentObject);
}
