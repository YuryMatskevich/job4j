package ru.job4j.array;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class SortArray {
    public int[] sort(int[] firstArray, int[] secondArray) {

        int fSize = firstArray.length;
        int sSize = secondArray.length;
        int[] array = new int[fSize + sSize];

        int fI = 0;
        int sI = 0;

        int[] current = null;
        int iCurrent = 0;

        for (int i = 0; i < fSize + sSize; i++) {
            if (fI == fSize | sI == sSize) {
                for (int y = iCurrent; y < current.length; y++) {
                    array[i] = current[y];
                    i++;
                }
            } else {
                if (firstArray[fI] < secondArray[sI]) {
                    array[i] = firstArray[fI];
                    fI++;
                    current = secondArray;
                    iCurrent = sI;
                } else {
                    array[i] = secondArray[sI];
                    sI++;
                    current = firstArray;
                    iCurrent = fI;
                }
            }
        }
        return array;
    }
}
