package ru.job4j.crud;

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
public class UserServlet extends HttpServlet {
	private final Validate valid = ValidateService.getInstance();
	private final Map<String, Handler> actions = new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("test/html");
		PrintWriter writer = new PrintWriter(resp.getWriter());
		List<User> list = valid.findAll();
		for (User item : list) {
			writer.append(item.toString());
		}
		writer.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		load(
				req.getParameter("id"),
				req.getParameter("name"),
				req.getParameter("login"),
				req.getParameter("email")
		);
		actions.get(action).execute();
	}

	private void load(String id, String name, String login, String email) {
		actions.put("add", () -> valid.add(
				new User(name, login, email, System.currentTimeMillis())));
		actions.put("update", () -> valid.update(
				new User(Integer.parseInt(id), name, login, email)));
		actions.put("delete", () -> valid.delete(Integer.parseInt(id)));
	}

	private interface Handler {
		/**
		 * Does some action like add, update, delete etc.
		 */
		void execute();
	}
}
