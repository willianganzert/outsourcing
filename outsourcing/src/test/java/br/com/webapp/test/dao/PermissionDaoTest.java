package br.com.webapp.test.dao;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import br.com.webapp.dao.PermissionDao;
import br.com.webapp.dao.UserDao;
import br.com.webapp.model.Permission;
import br.com.webapp.model.User;

public class PermissionDaoTest {

	@Test
	public void crudTest() {
		PermissionDao dao = new PermissionDao();

		Permission d = new Permission();
		d.setName("Permission junit1");
		d.setDescription("Permission junit1");

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
			d.setName("Permission junit2");
			d.setDescription("Permission junit2");
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
	public void crudWithUserTest() {
		PermissionDao dao = new PermissionDao();

		Permission d = new Permission();
		d.setName("Permission junit1");
		d.setDescription("Permission junit1");

		User u = null;

		try {
			UserDao userDao = new UserDao();
			u = userDao.findAll().get(0);
		} catch (Exception e) {
			Assert.fail("fail on find one user, create one user for this test");
		}

		// insert
		try {
			d.setUsers(new HashSet<User>());
			d.getUsers().add(u);
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
			d.setName("Permission junit2");
			d.setDescription("Permission junit2");
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
