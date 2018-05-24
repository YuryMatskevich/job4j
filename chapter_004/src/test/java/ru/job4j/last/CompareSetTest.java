package ru.job4j.last;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class CompareSetTest {

	@Test
	public void whenTwoSetsAreEqualEachOther() {
		String[] array1 = {"A", "B", "C", "D"};
		String[] array2 = {"B", "C", "A", "D"};
		CompareSet<String> compareSet = new CompareSet<>(array1, array2);
		assertTrue(compareSet.equalSet());
	}

	@Test
	public void whenTwoSetsAreNotEqualEachOther() {
		String[] array1 = {"A", "B", "C", "D", "E"};
		String[] array2 = {"B", "C", "A", "D"};
		CompareSet<String> compareSet = new CompareSet<>(array1, array2);
		assertFalse(compareSet.equalSet());
	}
}