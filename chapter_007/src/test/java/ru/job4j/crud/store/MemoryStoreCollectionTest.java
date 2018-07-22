package ru.job4j.crud.store;

/**
 * @author Yury Matskevich
 */
public class MemoryStoreCollectionTest extends MemoryStoreTest {
	@Override
	protected Store getStore() {
		return MemoryStore.getInstance();
	}
}
