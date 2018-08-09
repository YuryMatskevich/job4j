package ru.job4j.todo.servlets;

import org.apache.log4j.Logger;
import ru.job4j.todo.servlets.load.LoadResource;

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
public class CreateDbListner implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(CreateDbListner.class);
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
		dropSchema();
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/test",
					"postgres",
					"8088059"
			);
		} catch (SQLException | ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
		return conn;
	}

	private void createDb() {
		String query = readSqlScript(createScript);
		try (Connection connection = getConnection();
		Statement statement = connection.createStatement()) {
			statement.execute(query);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void dropSchema() {
		String query = readSqlScript(deleteScript);
		try (Connection conn = getConnection();
			 Statement statement = conn.createStatement()) {
			statement.execute(query);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

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
