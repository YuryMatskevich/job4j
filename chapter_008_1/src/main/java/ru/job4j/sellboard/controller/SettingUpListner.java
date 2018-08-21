package ru.job4j.sellboard.controller;

import org.apache.log4j.Logger;
import ru.job4j.sellboard.model.util.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Yury Matskevich
 */
public class SettingUpListner implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(SettingUpListner.class);
	private String createScript = getClass()
			.getClassLoader()
			.getResource("createScript.sql")
			.getPath();
	private String deleteScript = getClass()
			.getClassLoader()
			.getResource("deleteScript.sql")
			.getPath();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		createDb();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().close();
		dropSchema();
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/"
							+ "?user=root&password=8088059"
							+ "&useSSL=false"
							+ "&serverTimezone=UTC"
							+ "&allowMultiQueries=true"
			);
		} catch (SQLException | ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
		return conn;
	}

	/**
	 * Creates a db for a current webapp
	 */
	private void createDb() {
		String query = readSqlScript(createScript);
		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * Deletes an app db
	 */
	private void dropSchema() {
		String query = readSqlScript(deleteScript);
		try (Connection conn = getConnection();
			 Statement statement = conn.createStatement()) {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * Reads .sql script and represents it like a string
	 *
	 * @param path a path to the .sql sqript
	 * @return a string of .sql script
	 */
	private String readSqlScript(String path) {
		BufferedReader input = null;
		StringBuilder str = new StringBuilder();
		try {
			input = new BufferedReader(new FileReader(path));
			String s;
			while ((s = input.readLine()) != null) {
				str.append(s);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return str.toString();
	}
}
