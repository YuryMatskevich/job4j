package ru.job4j.storage.store;

import org.apache.log4j.Logger;
import ru.job4j.storage.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yury Matskevich
 */
public class JdbcStorage implements Store {
	private static final Logger LOG = Logger.getLogger(JdbcStorage.class);
	private String url;
	private String userName;
	private String password;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Connection getConnetion() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
	}

	private void closeConnection(Connection connection) {
		if (connection == null) {
			return;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public void addUser(User user) {
		String query = "insert into users (name_u, age_u) values (?, ?);";
		Connection connection = null;
		try {
			connection = getConnetion();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.execute();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public List<User> getAllUsers() {
		String query = "select * from users";
		List<User> users = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnetion();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setAge(resultSet.getInt(3));
				users.add(user);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			closeConnection(connection);
		}
		return users;
	}
}
