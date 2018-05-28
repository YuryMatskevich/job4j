package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yury Matskevich
 */
public abstract class Entity implements Runnable {
	private final ReentrantLock[][] lock;
	private final int xStart;
	private final int yStart;

	public Entity(ReentrantLock[][] lock, int x, int y) {
		this.lock = lock;
		xStart = x;
		yStart = y;
	}

	@Override
	public void run() {
		lock[yStart][xStart].lock(); //to block starting cell
	}

	public ReentrantLock[][] getLock() {
		return lock;
	}
}
