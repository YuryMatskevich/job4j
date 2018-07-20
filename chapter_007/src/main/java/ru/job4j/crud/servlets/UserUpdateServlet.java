package ru.job4j.crud.servlets;

import org.apache.log4j.Logger;
import ru.job4j.crud.User;
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
public class UserUpdateServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(UserUpdateServlet.class);
	private final Validate valid = ValidateService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		User user = valid.findById(Integer.parseInt(id));
		req.setAttribute("user", user);
		RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/view/update.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String login = req.getParameter("login");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String role = req.getParameter("roles");
		if (
				valid.update(
				new User(
						Integer.parseInt(id),
						name.equals("") ? null : name,
						login.equals("") ? null : login,
						email.equals("") ? null : email,
						password.equals("") ? null : password,
						role == null ? null : Integer.parseInt(role)
				)
		)) {
			resp.sendRedirect("/users");
		} else {
			req.setAttribute("error", "Invalid dates");
			doGet(req, resp);
		}
	}
}
