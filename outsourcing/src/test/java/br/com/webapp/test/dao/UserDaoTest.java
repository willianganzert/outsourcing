package br.com.webapp.test.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.webapp.dao.DepartmentDao;
import br.com.webapp.dao.UserDao;
import br.com.webapp.model.Department;
import br.com.webapp.model.User;

public class UserDaoTest {

	@Test
	public void crudTest() {
		UserDao dao = new UserDao();

		User d = new User();
		d.setName("User junit1");
		d.setDescription("User junit1");

		// insert
		try {
			dao.insertOrUpdate(d);
		} catch (Exception e) {
			Assert.fail("fail insert");
		}

		Assert.assertNotNull("id problem", d.getId());

		// find by id
		try {
			d = dao.findById(d.getId());
			if (d == null) {
				Assert.fail("return null on entity generated");
			}
		} catch (Exception e) {
			Assert.fail("fail find");
		}

		// update
		try {
			d.setName("User junit2");
			d.setDescription("User junit2");
			dao.insertOrUpdate(d);
		} catch (Exception e) {
			Assert.fail("fail update");
		}

		// delete
		try {
			dao.delete(d.getId());
		} catch (Exception e) {
			Assert.fail("fail delete");
		}
	}

	@Test
	public void crudWithDepartmentTest() {
		UserDao dao = new UserDao();

		Department de = null;
		
		try {
			DepartmentDao depDao = new DepartmentDao();
			de = depDao.findAll().get(0);
		} catch (Exception e) {
			Assert.fail("fail on find one department, create one department for this test");
		}

		User d = new User();
		d.setName("User junit1");
		d.setDescription("User junit1");
		d.setDepartment(de);
		
		// insert
		try {
			dao.insertOrUpdate(d);
		} catch (Exception e) {
			Assert.fail("fail insert");
		}

		Assert.assertNotNull("id problem", d.getId());

		// find by id
		try {
			d = dao.findById(d.getId());
			if (d == null) {
				Assert.fail("return null on entity generated");
			}
		} catch (Exception e) {
			Assert.fail("fail find");
		}

		// update
		try {
			d.setName("User junit2");
			d.setDescription("User junit2");
			dao.insertOrUpdate(d);
		} catch (Exception e) {
			Assert.fail("fail update");
		}

		// delete
		try {
			dao.delete(d.getId());
		} catch (Exception e) {
			Assert.fail("fail delete");
		}
	}
}
