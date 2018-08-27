package ru.job4j.storage;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.entity.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 */
public class ImportUserTest {
	private User user;

	@Before
	public void setUp() {
		user = new User();
		user.setId(1);
		user.setName("name");
		user.setAge(20);
	}

	//gives first user from List of users
	private User getOneUserFromList(List<User> users) {
		return users.get(0);
	}

	@Test
	public void whenAddUserToStoreThenThereIsNewUserInStore() {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("Storage-SpringContext.xml");
		ImportUser importUser = context.getBean("mainApp", ImportUser.class);
		importUser.addUser(user);
		assertEquals(getOneUserFromList(importUser.getAllUser()), user);
	}
}