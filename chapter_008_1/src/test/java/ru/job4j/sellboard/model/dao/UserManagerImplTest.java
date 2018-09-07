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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Yury Matskevich
 */
public class UserManagerImplTest {
	private final UserManager userManager = new UserManagerImpl();

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
		return user;
	}

	//returns one existing user from db
	private User getSingleUserFromDb() {
		return userManager.getAllUsers().get(0);
	}

	@Test
	public void createNewUserTest() {
		assertEquals(
				"They should be the same users",
				createTestUser(),
				getSingleUserFromDb()
		);
	}

	@Test
	public void findUserByIdTest() {
		int id = getSingleUserFromDb().getId();
		assertNotNull(userManager.findUserById(id));
	}

	@Test
	public void updateUserTest() {
		User alterUser = new User("alterUser", Role.ADMIN);
		alterUser.setId(getSingleUserFromDb().getId());
		alterUser.setCredential(
				new Credential(
						"alterLogin",
						"alterPassword"
				)
		);
		userManager.updateUser(alterUser);
		assertEquals(
				"They should be the same users",
				alterUser,
				getSingleUserFromDb()
		);
	}

	@Test
	public void deleteUserTest() {
		int id = getSingleUserFromDb().getId();
		userManager.deleteUser(id);
		assertTrue(
				"A db should be empty",
				userManager.getAllUsers().isEmpty()
		);
	}

	@Test
	public void addNewAdToUserTest() {
		Ad ad = new Ad(
				"test",
				100.5,
				false,
				123L,
				null
		);
		ad.setCar(
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
		int id = getSingleUserFromDb().getId();
		userManager.addNewAdToUser(id, ad);
		assertEquals(
				"They should be the same ads",
				getSingleUserFromDb().getAds().get(0),
				ad
		);
	}

	@Test
	public void deleteAdFromUserTest() {
		Ad ad = new Ad(
				"test",
				100.5,
				false,
				123L,
				null
		);
		ad.setCar(
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
		int userId = getSingleUserFromDb().getId();
		userManager.addNewAdToUser(userId, ad);
		int adId = ad.getId();
		userManager.deleteAdFromUser(userId, adId);
		assertTrue(
				"A current user doesn't have any ads",
				getSingleUserFromDb().getAds().isEmpty()
		);
	}

	@Test
	public void getIdValidUserTest() {
		User user = getSingleUserFromDb();
		String login = user.getCredential().getLogin();
		String password = user.getCredential().getPassport();
		Integer expectedId = user.getId();
		assertEquals(
				"They should be the same ids",
				expectedId,
				userManager.getIdValidUser(login, password)
		);
	}
}