package ru.job4j.bomberman;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.Character;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 */
public class BoardTest {
	private PrintStream stdout = System.out;
	private ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before
	public void loadOutput() {
		System.setOut(new PrintStream(out));
	}

	@After
	public void backOut() {
		System.setOut(stdout);
	}

	@Test
	public void characterIsInScopeOf() throws InterruptedException {
		int xSize = 4;
		int ySize = 4;
		Board board = new Board(xSize, ySize);
		board.addEntity("heroAutoMove", 0, 0);
		board.addEntity("heroAutoMove", 3, 3);
		board.startGame();
		Thread.sleep(10000);
		board.stopGame();

		//all the steps have to be in scope of a game board
		String[] strings = new String(out.toByteArray()).split(System.lineSeparator());
		for (String item : strings) {
			int x = Integer.parseInt(Character.toString(item.charAt(3)));
			int y = Integer.parseInt(Character.toString(item.charAt(9)));
			assertTrue("Step in scope of board", x >= 0 & x <= xSize & y >= 0 & y <= ySize);
		}
	}
}