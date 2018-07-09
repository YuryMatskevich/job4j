package ru.job4j.crud.validate;

import ru.job4j.crud.User;
import ru.job4j.crud.store.MemoryStore;
import ru.job4j.crud.store.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yury Matskevich
 */
public class ValidateService implements Validate {
	private final Store store = MemoryStore.getInstance();
	private static ValidateService uniqueInstance =
			new ValidateService();

	private ValidateService() {

	}

	public static ValidateService getInstance() {
		return uniqueInstance;
	}

	@Override
	public boolean add(User user) {
		boolean result = false;
		if (!thereAreNotNorLorE(user)
				&& !existLoginOrEmail(user.getLogin(), user.getEmail())) {
			result = store.add(user);
		}
		return result;
	}

	@Override
	public boolean update(User user) {
		boolean result = false;
		if (findById(user.getId()) != null
				&& !existLoginOrEmail(user.getLogin(), user.getEmail())) {
			result = store.update(user);
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		if (findById(id) != null) {
			result = store.delete(id);
		}
		return result;
	}

	@Override
	public List<User> findAll() {
		return store.findAll();
	}

	@Override
	public User findById(int id) {
		return store.findById(id);
	}

	/**
	 * Checks if a current user doesn't have
	 * a name or a login or a email
	 * @param user a current user
	 * @return true if user
	 */
	private boolean thereAreNotNorLorE(User user) {
		return user.getName() == null
				|| user.getLogin() == null
				|| user.getEmail() == null;
	}

	/**
	 * Checks if a curent login and(or) an email exist(s) in
	 * the store
	 * @param login a current login
	 * @param email a current email
	 * @return true if the login and(or) the email exist(s) in
	 * the store, otherwise - false
	 */
	private boolean existLoginOrEmail(String login, String email) {
		boolean result = false;
		Set<String> logins = new HashSet<>(store.getLogins());
		Set<String> emails = new HashSet<>(store.getEmails());
		if (existElement(logins, login) || existElement(emails, email)) {
			result = true;
		}
		return result;
	}

	/**
	 * Checks is there a current element in a current collection
	 * @param collection a current collection
	 * @param elem a current element
	 * @param <T> a generalized parameter
	 * @return true if element is in the collection, otherwise - false
	 */
	/*
	I've done this method because I think it may be useful in future
	when I will have to know about existence any element in some collection
	*/
	private static <T> boolean existElement(Collection<T> collection, T elem) {
		return collection.contains(elem);
	}
}
