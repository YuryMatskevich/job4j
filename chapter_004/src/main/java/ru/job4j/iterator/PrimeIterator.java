package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class PrimeIterator implements Iterator {

    private final int[] values;
    private int iHas = 0;
    private boolean flag = false;

    public PrimeIterator(int[] values) {
        this.values = values;
    }

    private boolean checkPrimeNumber(int index) {
        boolean result = false;
        int current = values[index];
        int count = 0;
        for (int i = 1; i <= current; i++) {
            if (count == 3) {
                break;
            }
            if (current % i == 0) {
                count++;
            }
        }
        if (count == 2) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        if (!flag) {
            while (iHas < values.length) {
                if (checkPrimeNumber(iHas++)) {
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
