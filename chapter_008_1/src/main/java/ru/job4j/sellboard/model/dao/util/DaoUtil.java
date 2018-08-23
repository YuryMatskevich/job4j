package ru.job4j.sellboard.model.dao.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Yury Matskevich
 */
public class DaoUtil {
	/**
	 * Returns a start of a today's day
	 *
	 * @return <code>Date</code> of start of a day
	 * like a long type
	 */
	public static long getStartOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}
}
