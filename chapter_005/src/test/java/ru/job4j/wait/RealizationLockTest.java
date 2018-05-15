package ru.job4j.wait;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.jmm.MyThread;
import ru.job4j.jmm.Text;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class RealizationLockTest {
	private PrintStream stout = System.out;
	private ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		System.setOut(new PrintStream(out));
	}

	@After
	public void setBack() {
		System.setOut(stout);
	}

	/*
	Возьмем пример из задания про демонстрацию проблем с многопотчностью
	Но теперь метод класса Text имеет замок который закрывается когда поток
	начинает выполнять его (при этом другие потоки не могут его выполнять,
	они находятся в режиме "ожидани"), и открвается когда он его закончит.
	 */
	@Test
	public void testPrint() throws InterruptedException {
		Text text = new Text();
		new MyThread(text, "first");
		new MyThread(text, "second");
		new MyThread(text, "third");
		Thread.sleep(3000);

		Set<String> setExpected = new TreeSet<>(
				Arrays.asList("[first]", "[second]", "[third]")
		);
		Set<String> set = new TreeSet<>(
				Arrays.asList(
						new String(out.toByteArray())
								.split(System.lineSeparator())
				)
		);
		assertEquals(setExpected, set);
	}
}