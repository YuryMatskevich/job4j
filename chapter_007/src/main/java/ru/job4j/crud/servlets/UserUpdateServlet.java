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
public class UserUpdateServlet extends HttpServlet {
	private final Validate valid = ValidateService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = new PrintWriter(resp.getWriter());
		String id = req.getParameter("id");
		User user = valid.findById(Integer.parseInt(id));
		writer.append(
				"<!DOCTYPE html>\n"
						+ "<html lang=\"en\">\n"
						+ "<head>\n"
						+ "    <meta charset=\"UTF-8\">\n"
						+ "    <title>Update user</title>\n"
						+ "</head>\n"
						+ "<body>\n"
						+ "	<h1>Updating a user</h1>"
						+ "	<form action='" + req.getContextPath() + "/update' method='POST'>\n"
						+ "		<input type='hidden' name='id' value='" + user.getId() + "'>"
						+ "		Name: <input type='text' name='name' value='" + user.getName() + "'><br><br>\n"
						+ "		Login: <input type='text' name='login' value='" + user.getLogin() + "'><br><br>\n"
						+ "		Email: <input type='text' name='email' value='" + user.getEmail() + "'><br><br>\n"
						+ "		<input type='submit' value='update'>\n"
						+ "	</form>\n"
						+ "</body>\n"
						+ "</html>"
		);
		writer.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String login = req.getParameter("login");
		String email = req.getParameter("email");
		valid.update(new User(Integer.parseInt(id), name, login, email));
		resp.sendRedirect(req.getContextPath() + "/users");
	}
}
