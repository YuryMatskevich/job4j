package ru.job4j.storage.store;

import ru.job4j.storage.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Yury Matskevich
 */
public class MemoryStorage implements Store {
	private final List<User> userStore = new CopyOnWriteArrayList<>();

	@Override
	public void addUser(User user) {
		userStore.add(user);
	}

	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(userStore);
	}
}
