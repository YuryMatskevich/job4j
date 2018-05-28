package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yury Matskevich
 */
public class Hero extends Character {
	public Hero(ReentrantLock[][] lock, IMoveable typeMove, long maxWaitTime, int x, int y) {
		super(lock, typeMove, maxWaitTime, x, y);
	}
}