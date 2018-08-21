package ru.job4j.sellboard.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Yury Matskevich
 */
@Entity
@Table(name = "credential")
public class Credential {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cr")
	private int id;

	@Column(name = "login_cr")
	private String login;

	@Column(name = "passport_cr")
	private String passport;

	@OneToOne(
			mappedBy = "credential",
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
			}
	)
	private User users;

	public Credential() {
	}

	public Credential(String login, String passport) {
		this.login = login;
		this.passport = passport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Credential that = (Credential) o;
		return Objects.equals(login, that.login)
				&& Objects.equals(passport, that.passport);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, passport);
	}
}
