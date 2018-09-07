package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.controller.utils.AppUtils;
import ru.job4j.sellboard.model.dao.AdManager;
import ru.job4j.sellboard.model.dao.AdManagerImpl;
import ru.job4j.sellboard.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yury Matskevich
 */
public class CarsController extends HttpServlet {
	private final AdManager adManager = new AdManagerImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = AppUtils.getLoginedUser(req.getSession());
		if (user != null) {
			req.setAttribute("logenedUser", user);
		} else {
			req.setAttribute("logenedUser", null);
		}
		req.setAttribute("ads", adManager.getAllAds());
		req.getRequestDispatcher("/WEB-INF/view/carsList.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		adManager.delteAd(id);
		doGet(req, resp);
	}
}
