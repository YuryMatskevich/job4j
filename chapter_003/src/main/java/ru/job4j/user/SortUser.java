package ru.job4j.user;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class SortUser {
    private User[] insertionSort(List<User> user) {
        User[] array = new User[user.size()];
        int i = 0;
        for (User item : user) {
            array[i++] = item;
        }
        for (int out = 1; out < array.length; out++) {
            User temp = array[out];
            int in = out;
            while (in > 0 && array[in - 1].compareTo(temp) >= 0) {
                array[in] = array[in - 1];
                in--;
            }
            array[in] = temp;
        }
        return array;
    }

    public Set<User> sort(List<User> user) {
        Set<User> result = new TreeSet<>();
        for (User item : insertionSort(user)) {
            result.add(item);
        }
        return result;
    }
}
