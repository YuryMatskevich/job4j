package ru.job4j.crud.validate;

import ru.job4j.crud.store.MemoryStore;
import ru.job4j.crud.store.Store;

/**
 * @author Yury Matskevich
 */
public class ValidateServiceInstanceTest extends ValidateServiceTest {
	@Override
	protected Validate getValidate() {
		return ValidateService.getInstance();
	}

	@Override
	protected Store getStore() {
		return MemoryStore.getInstance();
	}
}
