package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class EventIterator implements Iterator {

    private final int[] values;
    private int iHas = 0;
    private boolean flag = false;

    public EventIterator(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        if (!flag) {
            while (iHas < values.length) {
                if (values[iHas++] % 2 == 0) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        flag = false;
        return values[iHas - 1];
    }
}
