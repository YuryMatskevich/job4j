package ru.job4j.generic;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class UserStore extends AbstractStore<User> implements Store<User> {

    public UserStore(int maxSize) {
        super(maxSize);
    }

    @Override
    public void add(User model) {
        this.getArray().add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        this.getArray().set(this.findIndex(id), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        this.getArray().delete(this.findIndex(id));
        return true;
    }

    @Override
    public User findById(String id) {
        return getArray().get(this.findIndex(id));
    }
}
