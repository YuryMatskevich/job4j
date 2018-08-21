package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.model.dao.AdManager;
import ru.job4j.sellboard.model.dao.AdManagerImpl;
import ru.job4j.sellboard.model.entity.Ad;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Yury Matskevich
 */
public class LoadPhotoController extends HttpServlet {
	AdManager adManager = new AdManagerImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fileId = req.getParameter("id");
		Ad ad = adManager.findById(Integer.parseInt(fileId));
		byte[] photo = ad.getPicture();
		resp.setContentType("image/jpeg");
		OutputStream os = resp.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		bos.write(photo, 0, photo.length);
		bos.close();
	}
}
