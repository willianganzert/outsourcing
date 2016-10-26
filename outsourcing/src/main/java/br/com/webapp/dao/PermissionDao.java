package br.com.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

import br.com.webapp.model.Permission;

public class PermissionDao {
	private EntityManagerFactory factory = null;
	private EntityManager entityManager = null;

	private void getEntityManager() {
		factory = Persistence.createEntityManagerFactory("test");
		entityManager = factory.createEntityManager();
	}

	public Permission insertOrUpdate(Permission permission) throws Exception {
		try {
			getEntityManager();
			entityManager.getTransaction().begin();

			if (permission.getId() == null) {
				entityManager.persist(permission);
			} else {
				permission = entityManager.merge(permission);
			}

			entityManager.getTransaction().commit();
		} finally {
			close();
		}
		return permission;
	}

	public void delete(Integer id) {
		try {

			getEntityManager();
			entityManager.getTransaction().begin();
			Permission permission = entityManager.find(Permission.class, id);

			entityManager.remove(permission);
			entityManager.getTransaction().commit();
		} finally {
			close();
		}
	}

	public Permission findById(Integer id) {
		Permission permission = null;
		try {
			getEntityManager();
			permission = entityManager.find(Permission.class, id);
		} finally {
			close();
		}
		return permission;
	}

	public List<Permission> findAll() {
		List<Permission> permissions = new ArrayList<>();
		try {
			getEntityManager();
			permissions = entityManager.createQuery("select e from Permission e").getResultList();
		} finally {
			close();
		}
		return permissions;
	}

	public void close() {
		try {
			entityManager.close();
		} finally {
			factory.close();
		}
	}
}
