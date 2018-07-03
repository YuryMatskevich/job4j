package ru.job4j.last.date;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Yury Matskevich
 */
public class DateConverter implements IDateConverter {
	private static final Logger LOG = Logger.getLogger(DateConverter.class);

	@Override
	public long getLongOfStartOfCurrentYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return getLongFromDayMonthYear("01jan" + year);
	}

	@Override
	public long convertInLong(String str) {
		long result = 0;
		if (str.matches("\\сегодня,\\s\\d+:\\d+")) {
			result = getLongFromDayMonthYear(createDateFromString(today()));
		}
		if (str.matches("\\вчера,\\s\\d+:\\d+")) {
			result = getLongFromDayMonthYear(createDateFromString(today())) - (24 * 60 * 60 * 1000);
		}
		if (str.matches("\\d+\\s[А-я]{3}\\s\\d{2},\\s\\d+:\\d+")) {
			result = getLongFromDayMonthYear(createDateFromString(str));
		}
		result += convertHoursAndMinutesToLong(str.split(",\\s")[1].split(":"));
		return result;
	}

	/*
	gets a long number from a date(which is in form of
	string with given formt(ddMMyy)).
	*/
	private long getLongFromDayMonthYear(String date) {
		SimpleDateFormat f = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
		long milliseconds = 0;
		try {
			java.util.Date d = f.parse(date);
			milliseconds = d.getTime();
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		return milliseconds;
	}

	//gets today's date in format of String
	private String today() {
		return LocalDateTime
				.now(ZoneId.of("Europe/Moscow"))
				.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ", 00:00";
	}

	//creates the necessary format of date
	private String createDateFromString(String date) {
		String[] array = date.split(",\\s")[0].split("\\s");
		return array[0] + convertFromRusToEng(array[1]) + array[2];
	}

	//gets a long number from diven hours and minutes
	private long convertHoursAndMinutesToLong(String[] time) {
		byte hours = (byte) Integer.parseInt(time[0]);
		byte minutes = (byte) Integer.parseInt(time[1]);
		return ((hours * 60 + minutes) * 60) * 1000;
	}

	//converts name of month from Russian to England
	private String convertFromRusToEng(String month) {
		String result;
		switch (month) {
			case "янв":
				result = "january";
				break;
			case "фев":
				result = "february";
				break;
			case "мар":
				result = "march";
				break;
			case "апр":
				result = "april";
				break;
			case "май":
				result = "may";
				break;
			case "июн":
				result = "june";
				break;
			case "июл":
				result = "july";
				break;
			case "авг":
				result = "august";
				break;
			case "сен":
				result = "september";
				break;
			case "окт":
				result = "october";
				break;
			case "ноя":
				result = "november";
				break;
			default:
				result = "december";

		}
		return result;
	}
}
