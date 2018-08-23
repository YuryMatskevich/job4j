package ru.job4j.sellboard.model.dao;

import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.enums.Sort;

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

	/**
	 * Finds all the ads with a photo
	 *
	 * @return a <code>List</code> of <code>Ad</code> with a photo
	 */
	List<Ad> findWithPhoto();

	/**
	 * Finds all today's ads
	 *
	 * @return a <code>List</code> of today's <code>Ad</code>s
	 */
	List<Ad> findTodayAd();

	/**
	 * Finds all the ads of a current type of a car
	 *
	 * @param sort a sort of a car
	 * @return a <code>List</code> of <code>Ad</code>s with
	 * a current type of car (for example: BMW, KIA etc.)
	 */
	List<Ad> findBySort(Sort sort);
}
