package ru.job4j.last;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
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

	//Текст в файлу в строку
	private String convertFileToString() throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(path));
		byte[] bytes = new byte[dis.available()];
		dis.readFully(bytes);
		return new String(bytes, 0, bytes.length);
	}

	//преобразуем стрку в массив, каждый новый элемент которого содержит след слово.
	private String[] createArrayOfStringWhichIncludsOnliWords() throws IOException {
		String[] cur = convertFileToString().split("\\W");
		List<String> temp = new ArrayList<>();
		for (String item : cur) {
			temp.add(item.toLowerCase());
		}
		return temp.toArray(new String[temp.size()]);
	}

	//возвращает Set позиций слова(позиция слова тексте: 1-первое слово, 2-второе слово и т.д.)
	private Set<Integer> getExpectedPosition(String word) throws IOException {
		Set<Integer> result = new TreeSet<>();
		int i = 0;
		for (String item : createArrayOfStringWhichIncludsOnliWords()) {
			i++;
			if (item.equals(word)) {
				result.add(i);
			}
		}
		return result;
	}

	@Test
	public void whenSearchForWordWhichConsistsOfOneLatterAndExistsInLoadedText() throws IOException {
		String search = "a";
		assertEquals(index.getIndexes4Word(search), getExpectedPosition(search));
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
	public void whenSearchForWordWhichExistsInLoadedTextThenReturnsSetOfPositionThisWordInTheText() throws IOException {
		String search = "called";
		assertEquals(index.getIndexes4Word(search), getExpectedPosition(search));
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