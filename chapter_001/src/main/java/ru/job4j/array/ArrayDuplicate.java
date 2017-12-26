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

        int nElem = array.length;

        for (int i = 0; i < nElem - 1; i++) {
            int j;
            for (j = i + 1; j < nElem; j++) {
                if (array[j].equals(array[i])) {
                    /*удаление первого найденного дубликата
                    (перезапись содержимого последующей ячейки
                     в предыдущую, начиная с первого найденного
                     дубликата)*/
                    for (int k = j; k < nElem - 1; k++) {
                        array[k] = array[k + 1];
                    }
                    nElem--;
                    /*повторная проверка элемента на позиции j
                     т.к. там теперь новое значение*/
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, nElem);
    }
}
