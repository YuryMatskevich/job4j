package ru.job4j.sellboard.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.sellboard.controller.SettingUpListner;
import ru.job4j.sellboard.model.dao.util.DaoUtil;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.Car;
import ru.job4j.sellboard.model.entity.Credential;
import ru.job4j.sellboard.model.entity.User;
import ru.job4j.sellboard.model.entity.enums.*;

import javax.servlet.ServletContextEvent;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author Yury Matskevich
 */
public class AdManagerImplTest {
	private UserManager userManager = new UserManagerImpl();
	private AdManager adManager = new AdManagerImpl();
	private User user;
	private Ad ad1;
	private Ad ad2;

	@Before
	public void setUp() {
		new SettingUpListner()
				.contextInitialized(
						mock(ServletContextEvent.class)
				);
		addTestUser();
		userManager.createNewUser(user);

	}

	@After
	public void backDown() {
		new SettingUpListner()
				.contextDestroyed(
						mock(ServletContextEvent.class)
				);
	}

	//adds a new user for testing
	private void addTestUser() {
		user = new User("user", Role.USER);
		user.setCredential(new Credential("user", "user"));
		ad1 = new Ad(
				"test1",
				100.5,
				false,
				DaoUtil.getStartOfDay() + (12 * 60 * 60 * 1000),
				null
		);
		ad1.setCar(
				new Car(
						5,
						500000,
						Sort.RENO,
						Transmission.AT,
						Manipulator.LEFT,
						Engine.DIESEL,
						Frame.SEDAN
				)
		);
		ad1.setUser(user);
		ad2 = new Ad(
				"test2",
				500.5,
				false,
				321L,
				null
		);
		ad2.setCar(
				new Car(
						5,
						500000,
						Sort.BMW,
						Transmission.AMT,
						Manipulator.RIGHT,
						Engine.PETROL,
						Frame.HATCHBACK
				)
		);
		ad2.setUser(user);
		user.add(ad1);
		user.add(ad2);
	}

	@Test
	public void getAllAdsTest() {
		Set<Ad> expectedAds = new HashSet<>(Arrays.asList(ad1, ad2));
		Set<Ad> actualAds = new HashSet<>(adManager.getAllAds());
		assertEquals(
				"They should be the same set of ads",
				expectedAds,
				actualAds
		);
	}

	@Test
	public void delteAdTest() {
		int idAd = ad1.getId();
		adManager.delteAd(idAd);
		assertEquals(
				"They should be the same ads",
				ad2,
				adManager.getAllAds().get(0)
		);
	}

	@Test
	public void findByIdTest() {
		int id = ad1.getId();
		assertEquals(
				"They should be the same ads",
				ad1,
				adManager.findById(id)
		);
	}

	@Test
	public void updateAdTest() {
		int id = ad1.getId();
		Ad alterAd = new Ad(
				"alterTest",
				1000.5,
				true,
				DaoUtil.getStartOfDay() + (12 * 60 * 60 * 1000),
				null
		);
		alterAd.setCar(
				new Car(
						2,
						100000,
						Sort.KIA,
						Transmission.MT,
						Manipulator.LEFT,
						Engine.GAZ,
						Frame.COUPE
				)
		);
		alterAd.setId(id);
		adManager.updateAd(alterAd);
		assertEquals(
				"They should be the same ads",
				alterAd,
				adManager.findById(id)
		);
	}

	@Test
	public void findWithPhotoTest() {
		List<Ad> ads = adManager.findWithPhoto();
		assertTrue(
				"There is no any ads with a photo",
				ads.isEmpty()
		);
	}

	@Test
	public void findTodayAdTest() {
		List<Ad> ads = adManager.findTodayAd();
		assertEquals(
				"They should be the same ads",
				ad1,
				ads.get(0)
		);
	}

	@Test
	public void findBySortTest() {
		List<Ad> ads = adManager.findBySort(Sort.RENO);
		assertEquals(
				"They should be the same ads",
				ad1,
				ads.get(0)
		);
	}
}