package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.controller.utils.AppUtils;
import ru.job4j.sellboard.model.dao.UserManager;
import ru.job4j.sellboard.model.dao.UserManagerImpl;
import ru.job4j.sellboard.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Yury Matskevich
 */
public class LoginController extends HttpServlet {
	private UserManager userManager = new UserManagerImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		Integer userId = userManager.getIdValidUser(login, password);
		if (userId == null) {
			String errorMessage = "Invalid login and/or password";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
		} else {
			User user = userManager.findUserById(userId);
			HttpSession session = req.getSession();
			session.setAttribute("userId", user.getId());
			AppUtils.storeLoginedUser(session, user);
			resp.sendRedirect("/carsList");
		}
	}
}
