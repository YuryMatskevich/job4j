package ru.job4j.sellboard.model.dao;

import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.User;

import java.util.List;

/**
 * @author Yury Matskevich
 */
public interface UserDAO extends GenericDAO<User, Integer> {
	/**
	 * Adds an ad to the user with a current id
	 * @param id an id of a user
	 * @param ad an added ad to the user
	 */
	void addNewAdToUser(Integer id, Ad ad);

	/**
	 * Deletes a current ad from a user
	 * @param userId an user id
	 * @param adId an ad id
	 */
	void deleteAdFromUser(Integer userId, int adId);

	/**
	 * Deletes a current user from a db
	 * @param id an id a of current user
	 */
	void delteUser(Integer id);

	/**
	 * Finds all the users
	 * @return a <code>List</code> of <code>User</code>
	 */
	List<User> findAllUser();

	/**
	 * Returns an id of a valid user
	 * @return a user id which is existed in a db
	 */
	Integer getIdValidUser(String login, String passport);
}
