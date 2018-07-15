package ru.job4j.crud.servlets;

import org.apache.log4j.Logger;
import ru.job4j.crud.validate.Validate;
import ru.job4j.crud.validate.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yury Matskevich
 */
public class UsersServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(UsersServlet.class);
	private final Validate valid = ValidateService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("users", valid.findAll());
		RequestDispatcher view = req
				.getRequestDispatcher("/WEB-INF/view/users.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		valid.delete(Integer.parseInt(id));
		resp.sendRedirect("/");
	}
}
