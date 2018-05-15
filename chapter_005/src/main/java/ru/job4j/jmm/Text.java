package ru.job4j.jmm;

import ru.job4j.wait.ILock;
import ru.job4j.wait.RealizationLock;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class Text {
	private ILock lock = new RealizationLock();

    public void print(String text) {
    	lock.lock();
        System.out.format("[%s", text);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Прерван");
        }
        System.out.println("]");
        lock.unlock();
    }
}
