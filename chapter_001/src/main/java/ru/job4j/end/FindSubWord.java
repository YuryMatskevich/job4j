package ru.job4j.end;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class FindSubWord {
    boolean contains(String origin, String sub) {
        char[] chOrigin = origin.toCharArray();
        char[] chSub = sub.toCharArray();
        for (int i = 0; i <= chOrigin.length - chSub.length; i++) {
            int j = i;
            int goal = 0;
            int k = 0;
            while (k != chSub.length && chOrigin[j] == chSub[k]) {
                goal++;
                //данной условие для того, чтоб
                //метод не реагировал на единственное
                //совпадение, т.е. когда вместо слова,
                //sub передает одну букву.
                if (goal != 1) {
                    return true;
                }
                j++;
                k++;
            }
        }
        return false;
    }
}