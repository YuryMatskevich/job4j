package ru.job4j.crud.servlets;

import ru.job4j.crud.User;
import ru.job4j.crud.validate.Validate;
import ru.job4j.crud.validate.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Yury Matskevich
 */
public class UserCreateServlet extends HttpServlet {
	private final Validate valid = ValidateService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = new PrintWriter(resp.getWriter());
		writer.append(
				"<!DOCTYPE html>\n"
						+ "<html lang=\"en\">\n"
						+ "<head>\n"
						+ "    <meta charset=\"UTF-8\">\n"
						+ "    <title>Creating a user</title>\n"
						+ "</head>\n"
						+ "<body>\n"
						+ "	<h1>Creating a new user</h1>"
						+ "	<form action='" + req.getContextPath() + "/create' method='POST'>\n"
						+ "		Name: <input type='text' name='name'><br><br>\n"
						+ "		Login: <input type='text' name='login'><br><br>\n"
						+ "		Email: <input type='text' name='email'><br><br>\n"
						+ "		<input type='submit' value='create'>\n"
						+ "	</form>\n"
						+ "</body>\n"
						+ "</html>"
		);
		writer.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String login = req.getParameter("login");
		String email = req.getParameter("email");
		valid.add(new User(name, login, email, System.currentTimeMillis()));
		resp.sendRedirect(req.getContextPath() + "/users");
	}
}
