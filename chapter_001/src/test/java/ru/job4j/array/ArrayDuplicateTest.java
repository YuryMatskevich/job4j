package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] array = {"Привет", "Пока", "Пока", "Пока", "Привет", "Привет", "Привет", "Пока", "Мир"};
        String[] expectArray = {"Привет", "Пока", "Мир"};
        assertThat(arrayDuplicate.remove(array), is(expectArray));
    }
}
