package ru.job4j.crud.validate;

import org.apache.log4j.Logger;
import ru.job4j.crud.User;
import ru.job4j.crud.store.DbStore;
import ru.job4j.crud.store.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yury Matskevich
 */
public class ValidateService implements Validate {
	private static final Logger LOG = Logger.getLogger(ValidateService.class);
	private final Store store = DbStore.getInstance();
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
		User updateUser = findById(user.getId());
		if (user.getRole() == null) {
			user.setRole(updateUser.getRole());
		}
		if (updateUser != null
				&& (usersWithSameLoginAndOrName(updateUser, user)
				| !existLoginOrEmail(user.getLogin(), user.getEmail()))) {
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

	public Integer isCredential(String login, String password) {
		return store.isCredential(login, password);
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
	 * Compares the equality of the field(s) of a login and an email
	 * for two users
	 * @param user1 a first user for comparing
	 * @param user2 a second user for comparing
	 * @return true if a logins and(or) an emails of two users are
	 * eguals, otherwise - false
	 */
	private boolean usersWithSameLoginAndOrName(User user1, User user2) {
		return user1.getLogin().equalsIgnoreCase(user2.getLogin())
				| user1.getEmail().equalsIgnoreCase(user2.getEmail());
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
