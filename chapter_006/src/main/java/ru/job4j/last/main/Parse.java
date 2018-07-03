package ru.job4j.last.main;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.job4j.last.config.Config;
import ru.job4j.last.connection.NoSuchDbException;
import ru.job4j.last.connection.StoreFactory;
import ru.job4j.last.dao.Offer;
import ru.job4j.last.dao.OfferDao;
import ru.job4j.last.date.DateConverter;
import ru.job4j.last.date.IDateConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yury Matskevich
 */
public class Parse {
	private static final Logger LOG = Logger.getLogger(Parse.class);
	private String pagePath;
	private String regxSearch;
	private OfferDao offerDao;
	private IDateConverter dateConv = new DateConverter();

	public Parse(String config) throws NoSuchDbException {
		Config conf = new Config(config);
		pagePath = conf.getProperty("jdbc.url");
		regxSearch = conf.getProperty("regx.search");
		String dbName = conf.getProperty("jdbc.dbName");
		offerDao = new OfferDao(new StoreFactory(config).createConnection(dbName));
	}

	/*
	Finds the all offers if it's a first launch or finds new offers
	if it's not a first launch
	 */
	public void findOffers() {
		LOG.info("Start finding new offers");
		List<Offer> offers = new ArrayList<>();
		int maxPage = getNumberOfMaxPage();
		long lastDate = offerDao.getTheNewestDate();
		long dateStartYear = dateConv.getLongOfStartOfCurrentYear();
		f:
		for (int page = 1; page <= maxPage; page++) {
			Document doc = getDocumentOfCurrentPage(page);
			Elements elems = doc.select("table.forumTable tbody tr");
			for (int i = 4; i < elems.size(); i++) {
				if (stopSearching(lastDate, dateStartYear, elems, i)) {
					break f; //returns from inner loop to mark of f
				}
				Elements subElems = elems.get(i).select("td.postslisttopic a");
				String head = subElems.text();
				if (checkKeyWord(head)) {
					String id = subElems.attr("href");
					try {
						String description = Jsoup
								.connect(id)
								.get()
								.select("td.msgBody")
								.get(1)
								.text();
						long create = dateConv.convertInLong(
								elems.get(i).select("td.altCol").get(1).text()
						);
						offers.add(new Offer(id, head, description, create));
					} catch (IOException e) {
						LOG.error(e.getMessage(), e);
						break f;
					}
				}
			}
		}
		addToDb(offers);
	}

	//adds to the db list of offers
	private void addToDb(List<Offer> offers) {
		if (offers.isEmpty()) {
			LOG.info("There is no new offers for adding");
			return;
		}
		offerDao.add(offers);
	}

	//a condition for stopping of searching
	private boolean stopSearching(
			long lastDate, long dateStartYear, Elements elems, int i) {
		long dateCurrOffer = dateConv.convertInLong(
				elems.get(i).select("td.altCol").get(1).text()
		);
		return dateCurrOffer < dateStartYear | dateCurrOffer <= lastDate;
	}

	//gets numbers of a last page in the section
	private int getNumberOfMaxPage() {
		Document doc = null;
		int result = 1;
		try {
			doc = Jsoup.connect(
					String.format("%s/%d", pagePath, 1)
			).get();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		if (doc != null) {
			result = Integer.parseInt(
					doc.select("table.sort_options a").last().text()
			);
		}
		return result;
	}

	//gets content of a current page
	private Document getDocumentOfCurrentPage(int page) {
		Document doc = null;
		try {
			doc = Jsoup.connect(
					String.format("%s/%d", pagePath, page)
			).get();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return doc;
	}

	/**
	 * Checks if the string contains the search word.
	 *
	 * @param str A search word.
	 * @return true if string contains the search word,
	 * else - otherwise
	 */
	private boolean checkKeyWord(String str) {
		Pattern p = Pattern.compile(
				regxSearch,
				Pattern.CASE_INSENSITIVE
		);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}
