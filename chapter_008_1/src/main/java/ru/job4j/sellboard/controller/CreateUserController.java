package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.model.dao.UserManager;
import ru.job4j.sellboard.model.dao.UserManagerImpl;
import ru.job4j.sellboard.model.entity.Credential;
import ru.job4j.sellboard.model.entity.User;
import ru.job4j.sellboard.model.entity.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yury Matskevich
 */
public class CreateUserController extends HttpServlet {
	private UserManager userManager = new UserManagerImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/createUser.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		if (userManager.getIdValidUser(login, password) == null) {
			User user = new User(name, Role.USER);
			user.setCredential(new Credential(login, password));
			userManager.createNewUser(user);
			resp.sendRedirect("/login");
		} else {
			req.setAttribute("errorMessage", "There is such a user with the same login or email");
			doGet(req, resp);
		}
	}
}
