package ru.job4j.generic;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public abstract class Base {
    private final String id;
    protected Base(final String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
