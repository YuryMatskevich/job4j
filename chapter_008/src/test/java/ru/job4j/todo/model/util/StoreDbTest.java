package ru.job4j.todo.model.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.todo.model.entity.Item;
import ru.job4j.todo.servlets.CreateDbListner;

import javax.servlet.ServletContextEvent;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Yury Matskevich
 */
public class StoreDbTest {
	private final StoreDb store = StoreDb.getInstance();
	private Item item = new Item("It's first item", new Timestamp(321L), false);

	@Before
	public void setUp() {
		new CreateDbListner()
				.contextInitialized(
						mock(ServletContextEvent.class)
				);
		store.createItem(item);
	}

	@After
	public void backDown() {
		new CreateDbListner()
				.contextDestroyed(
						mock(ServletContextEvent.class)
				);
	}


	@Test
	public void createNewItemTest() {
		Item item = store.listItem().get(0);
		assertEquals(this.item, item);
	}

	@Test
	public void listNotDoneItemTest() {
		store.createItem(new Item("It's second item", new Timestamp(321L), true));
		Item item = store.listNoteDoneItem().get(0);
		assertEquals(this.item, item);
	}

	@Test
	public void updateDoneTest() {
		int id = store.listItem().get(0).getId();
		store.updateDone(id, true);
		boolean done = store.listItem().get(0).isDone();
		assertTrue(done);
	}
}