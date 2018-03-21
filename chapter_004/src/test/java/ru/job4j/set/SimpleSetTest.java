package ru.job4j.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class SimpleSetTest {

    @Test(expected = NoSuchElementException.class)
    public void showSet() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("first");
        set.add("first");
        set.add("second");
        set.add("second");
        set.add("third");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
        assertThat(it.next(), is("third"));
        it.next();
    }
}