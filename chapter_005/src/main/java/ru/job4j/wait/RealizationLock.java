package ru.job4j.wait;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class RealizationLock implements ILock {
	private volatile boolean statusLock = false;

	@Override
	public void lock() {
		while (true) {
			if (!statusLock) {
				statusLock = true;
				break;
			}
		}
	}

	@Override
	public void unlock() {
		if (statusLock) {
			statusLock = false;
		}
	}
}
