package ru.job4j.sellboard.model.dao;

import org.hibernate.HibernateException;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.User;
import ru.job4j.sellboard.model.util.HibernateUtil;

import java.util.List;

/**
 * @author Yury Matskevich
 */
public class UserManagerImpl implements UserManager {
	private final UserDAO userDAO = new UserDAOImpl();

	@Override
	public void createNewUser(User user) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.create(user);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public User findUserById(Integer id) {
		User user = null;
		try {
			HibernateUtil.beginTransaction();
			user = userDAO.read(User.class, id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.update(user);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteUser(Integer id) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.delteUser(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void addNewAdToUser(Integer id, Ad ad) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.addNewAdToUser(id, ad);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void deleteAdFromUser(Integer userId, int adId) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.deleteAdFromUser(userId, adId);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			HibernateUtil.beginTransaction();
			users = userDAO.findAllUser();
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		}  finally {
			HibernateUtil.closeSession();
		}
		return users;
	}

	@Override
	public Integer getIdValidUser(String login, String passport) {
		Integer id = null;
		try {
			HibernateUtil.beginTransaction();
			id = userDAO.getIdValidUser(login, passport);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		}  finally {
			HibernateUtil.closeSession();
		}
		return id;
	}
}
