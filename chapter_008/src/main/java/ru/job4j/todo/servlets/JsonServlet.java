package ru.job4j.todo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todo.model.entity.Item;
import ru.job4j.todo.model.util.StoreDb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


/**
 * @author Yury Matskevich
 */
public class JsonServlet extends HttpServlet {
	private final StoreDb store = StoreDb.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cond = req.getParameter("cond");
		List<Item> items;
		if (cond.equals("all")) {
			items = store.listItem();
		} else {
			items = store.listNoteDoneItem();
		}
		resp.setContentType("text/json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), items);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String desc = req.getParameter("desc");
		if (desc != null) {
			store.createItem(
					new Item(
							desc,
							new Timestamp(System.currentTimeMillis()),
							false
					)
			);
		} else {
			String id = req.getParameter("id");
			String done = req.getParameter("done");
			store.updateDone(Integer.parseInt(id), done.equals("true"));
		}
	}
}
