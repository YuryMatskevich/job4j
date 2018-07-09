package ru.job4j.crud.store;

import ru.job4j.crud.User;

import java.util.List;
import java.util.Set;

/**
 * This interface allow some method for working
 * with a user's store
 * @author Yury Matskevich
 */
public interface Store {
	/**
	 * Adds a user to the store if the store does not contain
	 * user with an id equals to the user being added
	 * @param user an added user
	 * @return true if user has been added to the store, otherwise - false
	 */
	boolean add(User user);

	/**
	 * Updates a user if it exists in the store
	 * @param user an updated user
	 * @return true if user has been updated in the store, otherwise - false
	 */
	boolean update(User user);

	/**
	 * Deletes a user from the store
	 * @param id an id of user being aded
	 * @return true if user has been deleted from the store, otherwise - false
	 */
	boolean delete(int id);

	/**
	 * Gets all users who are in the store
	 * @return {@link List} of useres if there
	 * are users in the store, otherwise - null
	 */
	List<User> findAll();

	/**
	 * Gets a user with a current id
	 * @param id an id of the search user
	 * @return instance of {@link User} if there is
	 * user with a current id, otherwise - null
	 */
	User findById(int id);

	/**
	 * Gives all the logins which
	 * is existed in the store
	 * @return {@link List} of user's logins
	 */
	List<String> getLogins();

	/**
	 * Gives all the emails which
	 * is existed in the store
	 * @return {@link List} of user's emails
	 */
	List<String> getEmails();
}
