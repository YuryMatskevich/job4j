package ru.job4j.generic;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class RoleStore extends AbstractStore<Role> implements Store<Role> {

    public RoleStore(int maxSize) {
        super(maxSize);
    }

    @Override
    public void add(Role model) {
        this.getArray().add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        this.getArray().set(this.findIndex(id), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        this.getArray().delete(this.findIndex(id));
        return true;
    }

    @Override
    public Role findById(String id) {
        return this.getArray().get(this.findIndex(id));
    }

}
