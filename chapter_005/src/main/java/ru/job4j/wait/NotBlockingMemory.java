package ru.job4j.wait;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class NotBlockingMemory<T> {
	private ConcurrentHashMap<Integer, Model> map = new ConcurrentHashMap<>();

	public boolean add(int id, T entity) {
		return map.putIfAbsent(id, new Model(entity)) == null;
	}

	public boolean delete(int id) {
		return map.remove(id) != null;
	}

	public boolean update(int id, T entity) throws OptimisticException {
		boolean result = false;
		if (map.containsKey(id)) {
			Model model = map.get(id);
			int version = model.getVersion();
			/*
			Данный блок не входит в логику программы, добавлен лишь для того,
			чтоб обеспечить повторяемость результатов при демонстрации затирания
			данных одним пользователем, в то время как другой уже сделал изменения.
			 */
			try {
				try {
					Thread.sleep(Integer.parseInt(Thread.currentThread().getName()));
				} catch (NumberFormatException nfe) {
					//
				}
			} catch (InterruptedException e) {
				//
			}
			if (
					!(map.computeIfPresent(id,
						(key, value) ->
								version != value.getVersion()
										? value
										: model.modify(entity))
					).store.equals(entity)) {
				throw new OptimisticException("Optimistic exception");
			}
			result = true;
		}
		return result;
	}

	private class Model {
		private int version = 0;
		private T store;

		public Model(T store) {
			this.store = store;
		}

		public Model modify(T store) {
			this.store = store;
			version++;
			return this;
		}

		public int getVersion() {
			return version;
		}
	}
}
