package ru.job4j.array;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class Square {
    /**
     * Метод заполняет массив числами возведенными
     * в квадрат от 1 до bound.
     * @param bound параметр метода.
     * @return rst;
     * @see Square
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];

        for (int i = 0, ii = 1; i < bound; i++, ii++) {
            rst[i] = (int) Math.pow(ii, 2);
        }
        return rst;
    }
}