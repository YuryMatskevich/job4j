package ru.job4j.crud.store;

/**
 * @author Yury Matskevich
 */
public class MemoryStoreCollection extends MemoryStoreTest {
	@Override
	protected Store getStore() {
		return MemoryStore.getInstance();
	}
}
