package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.controller.utils.AppUtils;
import ru.job4j.sellboard.model.dao.AdManager;
import ru.job4j.sellboard.model.dao.AdManagerImpl;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.Car;
import ru.job4j.sellboard.model.entity.enums.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yury Matskevich
 */
public class UpdateAdController extends HttpServlet {
	private final AdManager adManager = new AdManagerImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Ad ad = adManager.findById(id);
		req.setAttribute("ad", ad);
		req.getRequestDispatcher("/WEB-INF/view/updateAd.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String sort = req.getParameter("sort");
		String frame = req.getParameter("frame");
		String engine = req.getParameter("engine");
		String transmission = req.getParameter("transmission");
		String manipulator = req.getParameter("manipulator");
		String age = req.getParameter("age");
		String way = req.getParameter("way");
		String description = req.getParameter("description");
		String price = req.getParameter("price");
		String state = req.getParameter("sold");
		if (!AppUtils.isThereEmptyField(
				sort,
				frame,
				engine,
				transmission,
				manipulator,
				age,
				way,
				description,
				price)) {
			Ad ad = new Ad(
					description,
					Double.parseDouble(price),
					state != null && state.equals("sold"),
					null
			);
			ad.setCar(
					new Car(
							Integer.parseInt(age),
							Integer.parseInt(way),
							Sort.valueOf(sort),
							Transmission.valueOf(transmission),
							Manipulator.valueOf(manipulator),
							Engine.valueOf(engine),
							Frame.valueOf(frame)
					)
			);
			ad.setId(Integer.parseInt(id));
			adManager.updateAd(ad);
			resp.sendRedirect("/carsList");
		} else {
			String errorMessage = "Invalid parameters";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/WEB-INF/view/createAd.jsp").forward(req, resp);
		}
	}
}
