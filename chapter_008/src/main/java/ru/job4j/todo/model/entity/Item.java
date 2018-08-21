package ru.job4j.todo.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Item {
	private int id;
	private String desc;
	private Timestamp created;
	private boolean done;

	public Item() {

	}

	public Item(String desc, Timestamp created, boolean done) {
		this.desc = desc;
		this.created = created;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Item item = (Item) o;
		return done == item.done
				&& Objects.equals(desc, item.desc)
				&& Objects.equals(created, item.created);
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc, created, done);
	}
}