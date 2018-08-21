package ru.job4j.sellboard.model.dao;

import ru.job4j.sellboard.model.entity.Ad;

import java.util.List;

/**
 * @author Yury Matskevich
 */
public interface AdManager {
	/**
	 * Gives all the ads
	 *
	 * @return <code>List</code> of <code>Ad</code>s
	 */
	List<Ad> getAllAds();

	/**
	 * Deletes an ad from a db via an ad id
	 *
	 * @param id an id of ad
	 */
	void delteAd(int id);

	/**
	 * Finds a current ad via an id
	 *
	 * @param id an id of current ad
	 * @return an <code>Ad</code> with current id
	 */
	Ad findById(int id);

	/**
	 * Updates a current ad
	 *
	 * @param ad an ad for updating
	 */
	void updateAd(Ad ad);
}
