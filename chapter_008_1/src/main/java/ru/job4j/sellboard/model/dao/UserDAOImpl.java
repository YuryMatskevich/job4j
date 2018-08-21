package ru.job4j.sellboard.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.Credential;
import ru.job4j.sellboard.model.entity.User;

import java.util.List;

/**
 * @author Yury Matskevich
 */
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {
	@Override
	public void addNewAdToUser(Integer id, Ad ad) {
		Session session = getSession();
		User user = session.get(User.class, id);
		user.add(ad);
		ad.setUser(user);
	}

	@Override
	public void deleteAdFromUser(Integer userId, int adId) {
		Session session = getSession();
		User user = session.get(User.class, userId);
		Ad deletingAd = null;
		for (Ad ad : user.getAds()) {
			if (ad.getId() == adId) {
				deletingAd = ad;
				break;
			}
		}
		if (deletingAd != null) {
			session.delete(deletingAd);
			user.getAds().remove(deletingAd);
		}
	}

	@Override
	public void delteUser(Integer id) {
		Session session = getSession();
		User user = session.get(User.class, id);
		session.delete(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAllUser() {
		Session session = getSession();
		return session.createQuery("from User").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Integer getIdValidUser(String login, String passport) {
		Session session = getSession();
		Query<Credential> query = session.createQuery(
				"from Credential where login =: paramLogin and passport =: paramPassport"
		);
		query.setParameter("paramLogin", login);
		query.setParameter("paramPassport", passport);
		List<Credential> credentials = query.getResultList();
		return credentials.isEmpty() ? null : credentials.get(0).getId();
	}
}
