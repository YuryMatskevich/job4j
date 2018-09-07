package ru.job4j.sellboard.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.sellboard.controller.SettingUpListner;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.Car;
import ru.job4j.sellboard.model.entity.Credential;
import ru.job4j.sellboard.model.entity.User;
import ru.job4j.sellboard.model.entity.enums.*;

import javax.servlet.ServletContextEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author Yury Matskevich
 */
public class AdManagerImplTest {
	private final UserManager userManager = new UserManagerImpl();
	private final AdManager adManager = new AdManagerImpl();

	@Before
	public void setUp() {
		new SettingUpListner()
				.contextInitialized(
						mock(ServletContextEvent.class)
				);
		deleteAdmin();
		userManager.createNewUser(createTestUser());

	}

	@After
	public void backDown() {
		new SettingUpListner()
				.contextDestroyed(
						mock(ServletContextEvent.class)
				);
	}

	//deletes an ADMIN from db
	private void deleteAdmin() {
		userManager.deleteUser(1);
	}

	//creates a new user for testing
	private User createTestUser() {
		User user = new User("user", Role.USER);
		user.setCredential(new Credential("user", "user"));
		Ad ad1 = new Ad(
				"test1",
				100.5,
				false,
				123L,
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
		Ad ad2 = new Ad(
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
		return user;
	}

	@Test
	public void getAllAdsTest() {

		Set<Ad> expectedAds = new HashSet<>(userManager.getAllUsers().get(0).getAds());
		Set<Ad> actualAds = new HashSet<>(adManager.getAllAds());
		assertEquals(
				"They should be the same set of ads",
				expectedAds,
				actualAds
		);
	}

	@Test
	public void delteAdTest() {
		int idAd1 = adManager.getAllAds().get(0).getId();
		int idAd2 = adManager.getAllAds().get(1).getId();
		adManager.delteAd(idAd1);
		adManager.delteAd(idAd2);
		assertTrue(
				"There is no ads",
				adManager.getAllAds().isEmpty()
		);
	}

	@Test
	public void findByIdTest() {
		int idDelete = adManager.getAllAds().get(0).getId();
		adManager.delteAd(idDelete);
		Ad ad = adManager.getAllAds().get(0);
		assertEquals(
				"They should be the same ads",
				ad,
				adManager.findById(ad.getId())
		);
	}

	@Test
	public void updateAdTest() {
		Ad ad = adManager.getAllAds().get(0);
		int id = ad.getId();
		Ad alterAd = new Ad(
				"alterTest",
				1000.5,
				true,
				123L,
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
}