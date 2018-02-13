package ru.job4j.user;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(User user) {
        return Integer.compare(this.age, user.age);
    }
}
