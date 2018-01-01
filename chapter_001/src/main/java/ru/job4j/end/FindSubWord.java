package ru.job4j.end;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class FindSubWord {
    boolean contains(String origin, String sub) {
        char[] ch = origin.toCharArray();
        int j;
        for (int i = 0; i < ch.length - 1; i++) {
            StringBuilder currentWord = new StringBuilder();
            currentWord.append(ch[i]);
            j = i + 1;
            while (j < ch.length) {
                currentWord.append(ch[j]);
                if (currentWord.toString().equals(sub)) {
                    return true;
                }
                j++;
            }
        }
        return false;
    }
}
