package ru.job4j.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class SortUserTest {
    @Test
    public void sortByAge() {
        SortUser sort = new SortUser();
        List<User> user = new ArrayList<>();
        User user1 = new User("Yury", 27);
        User user2 = new User("Viktor", 25);
        User user3 = new User("Kiril", 19);
        User user4 = new User("Andrey", 17);
        user.add(user1);
        user.add(user2);
        user.add(user3);
        user.add(user4);
        User[] orderExpected = {user4, user3, user2, user1};
        int i = 0;
        for (User item : sort.sort(user)) {
            assertThat(item.equals(orderExpected[i++]), is(true));
        }
    }

    @Test
    public void sortByLengthName() {
        SortUser sort = new SortUser();
        List<User> user = new ArrayList<>();
        User user1 = new User("Andrey", 27);
        User user2 = new User("Yury", 25);
        User user3 = new User("Kiril", 18);
        User user4 = new User("Kostia", 19);
        User user5 = new User("Pavel", 17);
        user.add(user1);
        user.add(user2);
        user.add(user3);
        user.add(user4);
        user.add(user5);
        User[] orderExpected = {user2, user3, user5, user1, user4};
        int i = 0;
        for (User item : sort.sortNameLength(user)) {
            assertThat(item.equals(orderExpected[i++]), is(true));
        }
    }

    @Test
    public void sortByAllFields() {
        SortUser sort = new SortUser();
        List<User> user = new ArrayList<>();
        User user1 = new User("A", 27);
        User user2 = new User("Bc", 25);
        User user3 = new User("Ba", 40);
        User user4 = new User("A", 19);
        User user5 = new User("C", 17);
        user.add(user1);
        user.add(user2);
        user.add(user3);
        user.add(user4);
        user.add(user5);
        User[] orderExpected = {user4, user1, user3, user2, user5};
        int i = 0;
        for (User item : sort.sortByAllFields(user)) {
            assertThat(item.equals(orderExpected[i++]), is(true));
        }
    }
}
