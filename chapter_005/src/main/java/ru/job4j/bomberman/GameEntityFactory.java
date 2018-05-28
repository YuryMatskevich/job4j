package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yury Matskevich
 */
public class GameEntityFactory {
	private final ReentrantLock[][] lock;

	public GameEntityFactory(ReentrantLock[][] lock) {
		this.lock = lock;
	}

	/**
	 * Create a new entity for adding on the game board
	 * @param name name of the entity
	 * @param x the starting position on the field along coordinate line of x
	 * @param y the starting position on the field along coordinate line of y
	 * @return An entity depending on the parameters
	 */
	public Entity createEttity(String name, int x, int y) {
		Entity character = null;
		if (name.equals("heroAutoMove")) {
			character = new Hero(lock, new AutoMove(), 500, x, y);
		}
		return character;
	}
}
