package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class Board {
	private final ReentrantLock[][] board;
	private final List<Thread> threads = new ArrayList<>(); //A store for threads
	private final GameEntityFactory entities;

	/**
	 * Creating of game board
	 * @param xSize size of the board along coordinate line of x
	 * @param ySize size of the board along coordinate line of y
	 */
	public Board(int xSize, int ySize) {
		this.board = new ReentrantLock[ySize][xSize];
		entities =  new GameEntityFactory(board);
		initialLock();
	}

	/**
	 * Add character to the board
	 * @param name name of character
	 * @param x position along coordinate line of x
	 * @param y position along coordinate line of y
	 */
	public void addEntity(String name, int x, int y) {
		Entity cur = entities.createEttity(name, x, y);
		threads.add(new Thread(cur));
	}

	/**
	 * Starting of the game
	 */
	public void startGame() {
		for (Thread unit : threads) {
			unit.start();
		}
	}

	/**
	 * Stopping of the game
	 */
	public void stopGame() {
		for (Thread unit : threads) {
			unit.interrupt();
		}
	}

	//just for initialization of array of ReentrantLock[][]
	private void initialLock() {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[0].length; x++) {
				board[y][x] = new ReentrantLock();
			}
		}
	}
}
