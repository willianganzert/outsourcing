package br.com.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.webapp.model.User;

public class UserDao {
	private EntityManagerFactory factory = null;
	private EntityManager entityManager = null;

	private void getEntityManager() {
		factory = Persistence.createEntityManagerFactory("test");
		entityManager = factory.createEntityManager();
	}

	public User insertOrUpdate(User user) throws Exception {
		try {
			getEntityManager();
			entityManager.getTransaction().begin();

			if (user.getId() == null) {
				entityManager.persist(user);
			} else {
				user = entityManager.merge(user);
			}

			entityManager.getTransaction().commit();
		} finally {
			close();
		}
		return user;
	}

	public void delete(Integer id) {
		try {

			getEntityManager();
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, id);

			entityManager.remove(user);
			entityManager.getTransaction().commit();
		} finally {
			close();
		}
	}

	public User findById(Integer id) {
		User user = null;
		try {
			getEntityManager();
			user = entityManager.find(User.class, id);
		} finally {
			close();
		}
		return user;
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try {
			getEntityManager();
			users = entityManager.createQuery("select e from User e").getResultList();
		} finally {
			close();
		}
		return users;
	}

	public void close() {
		try {
			entityManager.close();
		} finally {
			factory.close();
		}
	}
}
