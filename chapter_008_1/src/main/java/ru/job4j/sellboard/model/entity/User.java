package ru.job4j.sellboard.model.entity;

import ru.job4j.sellboard.model.entity.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yury Matskevich
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_u")
	private int id;

	@Column(name = "name_u")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "role_u")
	private Role role;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Ad> ads;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cr", unique = true)
	private Credential credential;

	public User() {
	}

	public User(String name, Role role) {
		this.name = name;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public void add(Ad ad) {
		if (ads == null) {
			ads = new ArrayList<>();
		}
		ads.add(ad);
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
		return Objects.equals(name, user.name)
				&& role == user.role
				&& Objects.equals(credential, user.credential);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, role, credential);
	}
}
