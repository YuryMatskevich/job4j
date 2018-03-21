package ru.job4j.set;

import ru.job4j.list.DynamicArray;
import ru.job4j.list.DynamicArrayIterator;

import java.util.Iterator;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class SimpleSet<E> implements Iterable<E> {

    private DynamicArray<E> array = new DynamicArray<>();

    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator<>(array);
    }

    public void add(E e) {
        for (E item : array) {
            if (e == item) {
                return;
            }
        }
        array.add(e);
    }
}