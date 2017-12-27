package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * Метод удаляет дубликаты в массиве
     * @param array массив строк
     * @return массив строк без дубликатов
     * @see ArrayDuplicate
     */
    public String[] remove(String[] array) {

        int size = array.length;
        int cut = array.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (array[i] == array[j] & i != j) {
                    if (j != size - 1) {
                        String cur = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = cur;
                    } else {
                        cut--;
                    }
                }
            }
            size--;
        }
        return Arrays.copyOf(array, cut);
    }
}
