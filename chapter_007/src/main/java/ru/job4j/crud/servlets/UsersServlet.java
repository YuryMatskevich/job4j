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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yury Matskevich
 */
public class UsersServlet extends HttpServlet {
	private final Validate valid = ValidateService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = new PrintWriter(resp.getWriter());
		StringBuilder sb = new StringBuilder("<table border='1'>");
		String path = req.getContextPath();
		//creates a content of user's table
		for (User user : valid.findAll()) {
			int id = user.getId();
			sb
					.append("<tr><td>")
					.append(user.toString())
					.append("</td><td>")
					.append("<form action='")
					.append(path)
					.append("/update' method='GET'>\n"
							+ "<input type='hidden' name='id' value='")
					.append(id)
					.append("'><input type='submit' value='update'></form>")
					.append("<form action='")
					.append(path)
					.append("/users' method='POST'>"
							+ "<input type='hidden' name='id' value='")
					.append(id)
					.append("'><input type='submit' value='delete'></form></td></tr>\n");
		}
		sb.append("</table>\n");
		writer.append(
				"<!DOCTYPE html>\n"
						+ "<html lang=\"en\">\n"
						+ "<head>\n"
						+ "    <meta charset=\"UTF-8\">\n"
						+ "    <title>User's table</title>\n"
						+ "</head>\n"
						+ "<body>\n"
						+ "	<h1>A user's table</h1>\n"
						+ sb.toString()
						+ "	<form action='" + path + "/create' method='GET'>\n"
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
		String id = req.getParameter("id");
		valid.delete(Integer.parseInt(id));
		resp.sendRedirect(req.getContextPath() + "/users");
	}
}
