package ru.job4j.set;

import ru.job4j.list.DynamicArray;
import ru.job4j.list.DynamicArrayIterator;
import ru.job4j.list.NodeList;
import ru.job4j.list.NodeListIterator;

import java.util.Iterator;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class SimpleSetNode<E> implements Iterable<E> {
    private NodeList<E> nodeList = new NodeList<>();

    @Override
    public Iterator<E> iterator() {
        return new NodeListIterator<>(nodeList);
    }

    public void add(E e) {
        for (E item : nodeList) {
            if (e == item) {
                return;
            }
        }
        nodeList.add(e);
    }
}
