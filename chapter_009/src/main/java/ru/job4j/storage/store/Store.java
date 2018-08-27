package ru.job4j.storage.store;

import ru.job4j.storage.entity.User;

import java.util.List;

/**
 * An interface for working with any storage
 *
 * @author Yury Matskevich
 */
public interface Store {
	/**
	 * Adds a new user to a storage
	 *
	 * @param user a user is being added
	 */
	void addUser(User user);

	/**
	 * Gives all the users from a storage
	 *
	 * @return <code>List</code> of a {@link User}s from
	 * a store
	 */
	List<User> getAllUsers();
}
