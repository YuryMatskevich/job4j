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
}
