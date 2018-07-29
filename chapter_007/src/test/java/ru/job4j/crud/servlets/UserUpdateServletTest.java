package ru.job4j.crud.servlets;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.crud.User;
import ru.job4j.crud.store.DbStore;
import ru.job4j.crud.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Yury Matskevich
 */
public class UserUpdateServletTest {
	private Store store = DbStore.getInstance();

	@Before
	//cleaning the store before testing
	public void setUp() {
		for (User user : store.findAll()) {
			store.delete(user.getId());
		}
		User user = new User("name", "login", "email", 0, "pass", 2);
		store.add(user);
	}

	//gets id of a existing user in the store
	private String getId() {
		return Integer.toString(store.findAll().get(0).getId());
	}

	@Test
	public void updateUserTest() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		String id = getId();
		String name = "newName";
		String login = "newLogin";
		String email = "newEmail";
		String pass = "newPassword";
		String role = "2";
		when(request.getParameter("id")).thenReturn(id);
		when(request.getParameter("name")).thenReturn(name);
		when(request.getParameter("login")).thenReturn(login);
		when(request.getParameter("email")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(pass);
		when(request.getParameter("roles")).thenReturn(role);
		new UserUpdateServlet().doPost(request, response);
		User user = store.findAll().get(0); // gets a user wich has been added to the stiore
		assertEquals(user.getName(), name);
		assertEquals(user.getLogin(), login);
		assertEquals(user.getEmail(), email);
		assertEquals(user.getPassword(), pass);
		assertEquals(user.getRole().toString(), role);
	}
}