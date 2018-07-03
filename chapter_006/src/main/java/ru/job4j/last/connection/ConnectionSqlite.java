package ru.job4j.last.connection;

import org.apache.log4j.Logger;
import ru.job4j.last.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Yury Matskevich
 */
public class ConnectionSqlite implements IConnection, AutoCloseable {
	private static final Logger LOG = Logger.getLogger(ConnectionSqlite.class);
	//path to resource folder where is a db in.
	private String res = getClass().getClassLoader().getResource("offers.bd").getPath();
	private Connection conn;
	private String path;
	private String scriptTable;

	public ConnectionSqlite(Config conf) {
		path = conf.getProperty("jdbc.path");
		scriptTable = conf.getProperty("jdbc.createTable");
		createTable(connect());
	}

	//Changes a delemiter in a path from '\' to '/' if it is necessary
	private String convertPathIfNecessary() {
		String path = res;
		if (path.contains("\\")) {
			path = path.replace("\\", "/");
		}
		return path;
	}

	//create table
	private void createTable(Connection conn) {
		try (Statement st = conn.createStatement()) {
			st.execute(scriptTable);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public Connection connect() {
		try {
			conn = DriverManager.getConnection(
					String.format("%s%s", path, convertPathIfNecessary()));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		return conn;
	}

	@Override
	public void close() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
}
