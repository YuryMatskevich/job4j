package ru.job4j.sellboard.model.dao;

import org.hibernate.HibernateException;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.util.HibernateUtil;

import java.util.List;

/**
 * @author Yury Matskevich
 */
public class AdManagerImpl implements AdManager {
	private final AdDAO adDAO = new AdDAOImpl();

	@Override
	public List<Ad> getAllAds() {
		List<Ad> ads = null;
		try {
			HibernateUtil.beginTransaction();
			ads = adDAO.findAllAd();
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
		return ads;
	}

	@Override
	public void delteAd(int id) {
		try {
			HibernateUtil.beginTransaction();
			adDAO.delteAd(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Ad findById(int id) {
		Ad ad = null;
		try {
			HibernateUtil.beginTransaction();
			ad = adDAO.findById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
		return ad;
	}

	@Override
	public void updateAd(Ad ad) {
		try {
			HibernateUtil.beginTransaction();
			adDAO.updateAd(ad);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
