package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.controller.utils.AppUtils;
import ru.job4j.sellboard.model.dao.UserManager;
import ru.job4j.sellboard.model.dao.UserManagerImpl;
import ru.job4j.sellboard.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yury Matskevich
 */
public class MyCarsController extends HttpServlet {
	private UserManager userManager = new UserManagerImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = AppUtils.getLoginedUser(req.getSession());
		req.setAttribute("myCar", "true");
		req.setAttribute("logenedUser", user);
		req.setAttribute("ads", userManager.findUserById(user.getId()).getAds());
		req.getRequestDispatcher("/WEB-INF/view/carsList.jsp").forward(req, resp);
	}
}
