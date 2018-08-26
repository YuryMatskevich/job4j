package ru.job4j.beans;

import org.springframework.stereotype.Component;

/**
 * @author Yury Matskevich
 */
@Component
public class Second {
	private int id;
	private String name;
	private String age;

	public Second() {
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
