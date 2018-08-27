package ru.job4j.storage;

import ru.job4j.storage.entity.User;
import ru.job4j.storage.store.Store;

import java.util.List;

/**
 * @author Yury Matskevich
 */
public class ImportUser {
	private Store store;

	public ImportUser(Store store) {
		this.store = store;
	}

	public void addUser(User user) {
		store.addUser(user);
	}

	public List<User> getAllUser() {
		return store.getAllUsers();
	}
}
