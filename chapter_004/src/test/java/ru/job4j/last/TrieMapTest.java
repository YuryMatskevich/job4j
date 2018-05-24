package ru.job4j.last;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class TrieMapTest {
	private String path = Objects.requireNonNull(getClass()
			.getClassLoader()
			.getResource("test.txt"))
			.getPath();
	private WordIndex index;

	@Before
	public void setUp() throws FileNotFoundException {
		index = new WordIndex();
		index.loadFile(path);
	}

	@Test
	public void whenSearchForWordWhichConsistsOfOneLatterAndExistsInLoadedText() {
		Set<Integer> setExpected = new TreeSet<>(Arrays.asList(3, 15, 20, 28, 33, 40));
		assertEquals(index.getIndexes4Word("a"), setExpected);
	}

	@Test
	public void whenSearchForWordWhichConsistsOfOneLatterAndDoesNotExistInLoadedText() {
		assertNull(index.getIndexes4Word("z"));
	}

	@Test
	public void whenSearchForWordWhichConsistsOfOneLatterAndDoesNotExistAndThereIsAtLeasOneWordWhichBeginsWithThisLatterInLoadedText() {
		assertNull(index.getIndexes4Word("m"));
	}

	@Test
	public void whenSearchForWordWhichExistsInLoadedTextThenReturnsSetOfPositionThisWordInTheText() {
		Set<Integer> setExpected = new TreeSet<>(Arrays.asList(9, 22));
		assertEquals(index.getIndexes4Word("called"), setExpected);
	}

	@Test
	public void whenSearchForWordWhichDoesNotExistInLoadedTextButItBeginsLikeOtherExistingWordThenReturnsNull() {
		assertNull(index.getIndexes4Word("callider"));
	}

	@Test
	public void whenSearchForWordWhichDoesNotExistInLoadedText() {
		assertNull(index.getIndexes4Word("Zoo"));
	}
}