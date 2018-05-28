package ru.job4j.bomberman;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yury Matskevich
 */
@ThreadSafe
public abstract class Character extends Entity {
	private final List<int[]> stepHist = new ArrayList<>();
	private final IMoveable typeMove;
	private final long maxWaitTime; //how much is thread waiting for the lock?

	public Character(ReentrantLock[][] lock, IMoveable typeMove, long maxWaitTime, int x, int y) {
		super(lock, x, y);
		this.typeMove = typeMove;
		this.maxWaitTime = maxWaitTime;
		stepHist.add(new int[]{x, y});
	}

	/**
	 * Method for getting array of position of character on the game board
	 * @return array of current position of character
	 * where array[0] is position along x and array[1]
	 * is position along y
	 */
	private int[] curPosition() {
		return stepHist.get(stepHist.size() - 1);
	}

	/**
	 * Method for getting array of position of character on the game board
	 * one step back
	 * @return array of previous position of character
	 * where array[0] is position along x and array[1]
	 * is position along y
	 */
	private int[] prevPosition() {
		return stepHist.get(stepHist.size() - 2);
	}

	/**
	 * The method turns the step into incrementing of coordinate
	 * @param step step of the character (up, down, left, right)
	 * @return array of increment of coordinates which corresponds to the step
	 * @see Step
	 */
	public int[] getStepCoordintes(Step step) {
		int[] result = new int[2];
		switch (step) {
			case down:
				result[0] = 0;
				result[1] = -1;
				break;
			case up:
				result[0] = 0;
				result[1] = 1;
				break;
			case left:
				result[0] = -1;
				result[1] = 0;
				break;
			default:
				result[0] = 1;
				result[1] = 0;
				break;
		}
		return result;
	}

	//Getting step
	private Step getStep() {
		return Step.values()[typeMove.getDirection()];
	}

	//Character stay over board
	private boolean checkStepOverBoard(int x, int y) {
		ReentrantLock[][] lock = getLock();
		return y < 0
				|| y >= lock.length
				|| x < 0
				|| x >= lock[0].length;
	}

	/**
	 * Character does a step
	 * @throws InterruptedException if the current thread is interrupted
	 */
	public synchronized void move() throws InterruptedException {
		ReentrantLock[][] lock = getLock();
		int[] coord;
		coord = curPosition(); //current position
		int xTemp;
		int yTemp;
		do {
			int[] step = getStepCoordintes(getStep());
			xTemp = coord[0] + step[0];
			yTemp = coord[1] + step[1];
		} while (checkStepOverBoard(xTemp, yTemp)
				|| !lock[yTemp][xTemp].tryLock(maxWaitTime, TimeUnit.MILLISECONDS));
		stepHist.add(new int[]{xTemp, yTemp});
		System.out.format("x: %d, y: %d, %s\n", xTemp, yTemp, Thread.currentThread().getName());
		coord = prevPosition(); //previous position
		lock[coord[1]][coord[0]].unlock(); //unlock previous position
	}

	/**
	 * Starting new thread. Character does a step each a second
	 */
	@Override
	public void run() {
		super.run();
		try {
			while (!Thread.currentThread().isInterrupted()) {
				move();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// log
		}
	}
}
