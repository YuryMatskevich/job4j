package ru.job4j.threads;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class ThreadTest {

    @Test
    public void testThread() {
        String path = "..\\.gitignore";
        new NewThread(new SearchWord(path));
        new NewThread(new SearchButton(path));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван");
        }
        System.out.println("Главный поток завешен");
    }
}