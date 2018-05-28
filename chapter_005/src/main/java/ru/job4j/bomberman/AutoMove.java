package ru.job4j.bomberman;

import java.util.Random;

/** For character who moves automatically via Random generator
 * @author Yury Matskevich
 */
public class AutoMove implements IMoveable {
	private final Random random = new Random();

	/**
	 * @return one of the item from an enum {@link Step}
	 * which defins direction of moving
	 * @see Step
	 */
	@Override
	public int getDirection() {
		return random.nextInt(Step.values().length);
	}
}
