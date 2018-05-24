package ru.job4j.last;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class CompareSet<T extends Comparable> {
	private T[] firstSet;
	private T[] secondSet;

	public CompareSet(T[] firstSet, T[] secondSet) {
		this.firstSet = firstSet;
		this.secondSet = secondSet;
	}

	public boolean equalSet() {
		Set<T> set1 = new TreeSet<>(Arrays.asList(firstSet));
		Set<T> set2 = new TreeSet<>(Arrays.asList(secondSet));
		return set1.equals(set2);
	}
}
