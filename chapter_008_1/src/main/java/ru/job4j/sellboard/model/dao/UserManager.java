package ru.job4j.sellboard.model.dao;

import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.User;

import java.util.List;

/**
 * @author Yury Matskevich
 */
public interface UserManager {
	/**
	 * Creates a new user
	 * @param user a added user
	 */
	void createNewUser(User user);

	/**
	 * Finds a user by id
	 * @param id an id of current user
	 * @return a searching <code>User</code>
	 */
	User findUserById(Integer id);

	/**
	 * Updates a user from a db
	 * @param user a user for updating
	 */
	void updateUser(User user);

	/**
	 * Deletes a curent user from a db
	 * @param id a user id
	 */
	void deleteUser(Integer id);

	/**
	 * Adds a new ad to a user with curent id
	 * @param id an id of c current user
	 * @param ad an <code>Ad</code> is being added to the user
	 */
	void addNewAdToUser(Integer id, Ad ad);

	/**
	 * Deletes a current ad from a user
	 * @param userId an id of a user
	 * @param adId an id of a deleted ad
	 */
	void deleteAdFromUser(Integer userId, int adId);

	/**
	 * Gives all the user
	 * @return <code>List</code> of <code>User</code>
	 */
	List<User> getAllUsers();

	/**
	 * Gives an id of a valid user
	 * @param login a login of a valid user
	 * @param passport a passport of a valid user
	 * @return a user id which is existed in a db
	 */
	Integer getIdValidUser(String login, String passport);
}
