package ru.job4j.crud;

import java.util.Objects;

/**
 * A pojo object which represents
 * a user with specified attributes
 * @author Yury Matskevich
 */
public class User {
	private static int count = 0; //an unique counter for users
	private int id;
	private String name;
	private String login;
	private String email;
	private long createDate;

	/**
	 * Creates a new user without an id.
	 * An id will be null
	 * @param name a user's name
	 * @param login a user's login
	 * @param email a user's email
	 * @param createDate an date when user was created
	 */
	public User(String name, String login, String email, long createDate) {
		this.id = ++count;
		this.name = name;
		this.login = login;
		this.email = email;
		this.createDate = createDate;
	}

	/**
	 * Creates a new user without an id and a date of creating
	 * @param id a user's id
	 * @param name a user's name
	 * @param login a user's login
	 * @param email a user's email
	 */
	public User(int id, String name, String login, String email) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User{"
				+ "id=" + id
				+ ", name='" + name + '\''
				+ ", login='" + login + '\''
				+ ", email='" + email + '\''
				+ ", createDate=" + createDate
				+ '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return createDate == user.createDate
				&& Objects.equals(name, user.name)
				&& Objects.equals(login, user.login)
				&& Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, login, email, createDate);
	}
}
