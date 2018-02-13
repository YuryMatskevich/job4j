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
        assertThat(sort.sort(user).toString(), is(String.format("[%s, %s, %s, %s]", user4.toString(), user3.toString(), user2.toString(), user1.toString())));
    }
}
