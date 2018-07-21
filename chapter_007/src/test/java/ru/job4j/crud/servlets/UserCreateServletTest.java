package ru.job4j.crud.servlets;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.crud.User;
import ru.job4j.crud.store.DbStore;
import ru.job4j.crud.store.Store;
import ru.job4j.crud.validate.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Yury Matskevich
 */
public class UserCreateServletTest {
	private Store store = DbStore.getInstance();

	@Before
	//cleaning the store before testing
	public void setUp() {
		for (User user : store.findAll()) {
			store.delete(user.getId());
		}
	}

	@Test
	public void createNewUserTest() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		String name = "name";
		String login = "login";
		String email = "email";
		String pass = "password";
		String role = "2";
		when(request.getParameter("name")).thenReturn(name);
		when(request.getParameter("login")).thenReturn(login);
		when(request.getParameter("email")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(pass);
		when(request.getParameter("roles")).thenReturn(role);
		new UserCreateServlet().doPost(request, response);
		User user = store.findAll().get(0); // gets a user wich has been added to the stiore
		assertEquals(user.getName(), name);
		assertEquals(user.getLogin(), login);
		assertEquals(user.getEmail(), email);
		assertEquals(user.getPassword(), pass);
		assertEquals(user.getRole().toString(), role);
	}
}