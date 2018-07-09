package ru.job4j.crud.store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.crud.User;

import java.util.*;

/**
 * This class stores user in the {@link Map}
 * @author Yury Matskevich
 */
@ThreadSafe
public class MemoryStore implements Store {
	private static MemoryStore uniqueInstance =
			new MemoryStore();
	@GuardedBy("this")
	private final Map<Integer, User> store = new HashMap<>();
	//a list of logins in the store
	@GuardedBy("this")
	private List<String> logins = new ArrayList<>();
	//a list of emails in the store
	@GuardedBy("this")
	private List<String> emails = new ArrayList<>();

	private MemoryStore() {

	}

	/**
	 * Creates an unique instance of {@link MemoryStore}
	 * @return an instance of {@link MemoryStore}
	 */
	public static MemoryStore getInstance() {
		return uniqueInstance;
	}

	@Override
	public synchronized boolean add(User user) {
		addLoginAndEmailToList(user);
		user.setId(getUniqueKey()); //put in a new unique id in user which it is being added
		return store.put(user.getId(), user) == null;
	}

	@Override
	public synchronized boolean update(User user) {
		int curId = user.getId();
		User curUser = findById(curId);
		setUpUserFields(
				user,
				curUser.getName(),
				curUser.getLogin(),
				curUser.getEmail(),
				curUser.getCreateDate()
		);
		deleteLoginAndEmailFromList(curUser);
		addLoginAndEmailToList(user);
		return store.replace(curId, user).equals(curUser);
	}

	@Override
	public synchronized boolean delete(int id) {
		User curUser = findById(id);
		deleteLoginAndEmailFromList(curUser);
		return store.remove(id).equals(curUser);
	}

	@Override
	public synchronized List<User> findAll() {
		return new ArrayList<>(store.values());
	}

	@Override
	public synchronized User findById(int id) {
		return store.get(id);
	}

	@Override
	public synchronized List<String> getLogins() {
		return new ArrayList<>(logins);
	}

	@Override
	public synchronized List<String> getEmails() {
		return new ArrayList<>(emails);
	}

	/**
	 * Returns a unique id for a new user
	 * @return a value of the unigue id
	 * for a user is being added to the store
	 */
	private synchronized int getUniqueKey() {
		//returns a max key from store. If store is empty - 0
		int key = store.isEmpty() ? 0 : Collections.max(
				store.entrySet(), Map.Entry.comparingByKey()).getKey();
		key++;
		return key;
	}

	private void setUpUserFields(
			User user, String name, String login, String email, long create) {
		user.setName(user.getName() == null ? name : user.getName());
		user.setLogin(user.getLogin() == null ? login : user.getLogin());
		user.setEmail(user.getEmail() == null ? email : user.getEmail());
		user.setCreateDate(create);
	}

	/**
	 * Puts in a either login and/or an email in to a
	 * corresponding list if they are not there
	 * @param user a user with current login and email
	 */
	private synchronized void addLoginAndEmailToList(User user) {
		String login = user.getLogin();
		String email = user.getEmail();
		if (!logins.contains(login)) {
			logins.add(login);
		}
		if (!emails.contains(email)) {
			emails.add(email);
		}
	}

	/**
	 * Deletes a login and an email of current user
	 * from corresponding lists
	 * @param user a user with current login and email which
	 * are being deleted from the lists
	 */
	private synchronized void deleteLoginAndEmailFromList(User user) {
		logins.remove(user.getLogin());
		emails.remove(user.getEmail());
	}
}
