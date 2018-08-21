package ru.job4j.sellboard.model.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author Yury Matskevich
 */
@Entity
@Table(name = "ad")
public class Ad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_a")
	private int id;

	@Column(name = "describe_a")
	private String describe;

	@Column(name = "price_a")
	private double price;

	@Column(name = "state_c")
	private boolean state;

	@Column(name = "create_c")
	private long time;

	@Column(name = "picture_c")
	private byte[] picture;

	@Transient
	private String timeString;

	@ManyToOne(
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH,
			}
	)
	@JoinColumn(name = "id_u")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_c", unique = true)
	private Car car;

	public Ad() {
	}

	public Ad(String describe, double price, boolean state, long time, byte[] picture) {
		this.describe = describe;
		this.price = price;
		this.state = state;
		this.time = time;
		this.picture = picture;
	}

	public Ad(String describe, double price, boolean state, byte[] picture) {
		this.describe = describe;
		this.price = price;
		this.state = state;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picure) {
		this.picture = picure;
	}

	public String getTimeString() {
		return convertDateToString();
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Ad ad = (Ad) o;
		return Double.compare(ad.price, price) == 0
				&& state == ad.state
				&& time == ad.time
				&& Objects.equals(describe, ad.describe)
				&& Arrays.equals(picture, ad.picture);
	}

	@Override
	public int hashCode() {
		return Objects.hash(describe, price, state, time, picture);
	}

	private String convertDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return format.format(new Date(time));
	}
}
