package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.model.dao.AdManager;
import ru.job4j.sellboard.model.dao.AdManagerImpl;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.enums.Sort;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Yury Matskevich
 */
public class CarFilterController extends HttpServlet {
	private AdManager adManager = new AdManagerImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String today = req.getParameter("today");
		String photo = req.getParameter("withphoto");
		String sort = req.getParameter("sort");
		List<Ad> ads = null;
		if ((today != null ? 1 : 0)
				+ (photo != null ? 1 : 0)
				+ (!sort.equals("") ? 1 : 0) != 1) {
			String errorMessage = "Choose one item for searching";
			req.setAttribute("errorMessage", errorMessage);
		} else {
			if (today != null) {
				ads = adManager.findTodayAd();
			}
			if (photo != null) {
				ads = adManager.findWithPhoto();
			}
			if (!sort.equals("")) {
				ads = adManager.findBySort(Sort.valueOf(sort));
			}
		}
		req.setAttribute("ads", ads);
		req.setAttribute("filter", "filter");
		req.getRequestDispatcher("/WEB-INF/view/carsList.jsp").forward(req, resp);
	}
}
