package br.com.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.webapp.model.Department;

public class DepartmentDao {
	private EntityManagerFactory factory = null;
	private EntityManager entityManager = null;

	private void getEntityManager() {
		factory = Persistence.createEntityManagerFactory("test");
		entityManager = factory.createEntityManager();
	}

	public Department insertOrUpdate(Department department) throws Exception {
		try {
			getEntityManager();
			entityManager.getTransaction().begin();

			if (department.getId() == null) {
				entityManager.persist(department);
			} else {
				department = entityManager.merge(department);
			}

			entityManager.getTransaction().commit();
		} finally {
			close();
		}
		return department;
	}

	public void delete(Integer id) {
		try {

			getEntityManager();
			entityManager.getTransaction().begin();
			Department department = entityManager.find(Department.class, id);

			entityManager.remove(department);
			entityManager.getTransaction().commit();
		} finally {
			close();
		}
	}

	public Department findById(Integer id) {
		Department department = null;
		try {
			getEntityManager();
			department = entityManager.find(Department.class, id);
		} finally {
			close();
		}
		return department;
	}

	public List<Department> findAll() {
		List<Department> departments = new ArrayList<>();
		try {
			getEntityManager();
			departments = entityManager.createQuery("select e from Department e").getResultList();
		} finally {
			close();
		}
		return departments;
	}
	
	public List<Department> findByName(String name) {
		List<Department> departments = new ArrayList<>();
		try {
			getEntityManager();
			departments = entityManager.createQuery("select e from Department e where name like :name")
					.setParameter("name", name)
					.getResultList();
		} finally {
			close();
		}
		return departments;
	}

	public void close() {
		try {
			entityManager.close();
		} finally {
			factory.close();
		}
	}
}
