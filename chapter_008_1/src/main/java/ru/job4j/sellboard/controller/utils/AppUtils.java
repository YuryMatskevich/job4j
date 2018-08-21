package ru.job4j.sellboard.controller.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import ru.job4j.sellboard.model.entity.Ad;
import ru.job4j.sellboard.model.entity.Car;
import ru.job4j.sellboard.model.entity.User;
import ru.job4j.sellboard.model.entity.enums.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @author Yury Matskevich
 */
public class AppUtils {
	private static final Logger LOG = Logger.getLogger(AppUtils.class);

	// IStore user info in Session.
	public static void storeLoginedUser(HttpSession session, User loginedUser) {
		session.setAttribute("loginedUser", loginedUser);
	}

	// Get the user information stored in the session.
	public static User getLoginedUser(HttpSession session) {
		return (User) session.getAttribute("loginedUser");
	}

	public static boolean isThereEmptyField(String ... v) {
		boolean result = false;
		for (String item : v) {
			if (item.equals("")) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static Ad createNewAd(
			String sort,
			String frame,
			String engine,
			String transmission,
			String manipulator,
			String age,
			String way,
			String description,
			String price,
			byte[] file) {
		Ad ad = new Ad(
				description,
				Double.parseDouble(price),
				false,
				new Date().getTime(),
				file
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
		return ad;
	}
}
