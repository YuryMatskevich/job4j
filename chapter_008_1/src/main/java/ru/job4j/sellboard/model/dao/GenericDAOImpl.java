package ru.job4j.sellboard.model.dao;

import org.hibernate.Session;
import ru.job4j.sellboard.model.util.HibernateUtil;

import java.io.Serializable;

/**
 * @author Yury Matskevich
 */
@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	@Override
	public PK create(T o) {
		Session session = getSession();
		return (PK) session.save(o);
	}

	@Override
	public T read(Class type, PK id) {
		Session session = getSession();
		return (T) session.get(type, id);
	}

	@Override
	public void update(T o) {
		Session session = getSession();
		session.update(o);
	}

	@Override
	public void delete(T o) {
		Session session = getSession();
		session.delete(o);
	}
}
