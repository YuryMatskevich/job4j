package ru.job4j.sellboard.controller;

import org.apache.commons.io.IOUtils;
import ru.job4j.sellboard.controller.utils.AppUtils;
import ru.job4j.sellboard.model.dao.UserManager;
import ru.job4j.sellboard.model.dao.UserManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Yury Matskevich
 */
@MultipartConfig
public class CreateAdController extends HttpServlet {
	private UserManager userManager = new UserManagerImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/createAd.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String sort = req.getParameter("sort");
		String frame = req.getParameter("frame");
		String engine = req.getParameter("engine");
		String transmission = req.getParameter("transmission");
		String manipulator = req.getParameter("manipulator");
		String age = req.getParameter("age");
		String way = req.getParameter("way");
		String description = req.getParameter("description");
		String price = req.getParameter("price");
		Part file = req.getPart("file");
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
			HttpSession session = req.getSession();
			Integer userId = (Integer) session.getAttribute("userId");
			userManager.addNewAdToUser(
					userId,
					AppUtils.createNewAd(
							sort,
							frame,
							engine,
							transmission,
							manipulator,
							age,
							way,
							description,
							price,
							file.getSize() == 0 ? null : IOUtils.toByteArray(file.getInputStream())
					)
			);
			resp.sendRedirect("/carsList");
		} else {
			String errorMessage = "Invalid parameters";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/WEB-INF/view/createAd.jsp").forward(req, resp);
		}
	}
}
