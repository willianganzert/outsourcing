package br.com.webapp.test.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.webapp.dao.DepartmentDao;
import br.com.webapp.model.Department;

public class DepartmentDaoTest {

	@Test
	public void crudTest() {
		DepartmentDao dao = new DepartmentDao();

		Department d = new Department();
		d.setName("DepartmentTest");
		d.setDescription("DepartmentTest");
		
		try {
			dao.insertOrUpdate(d);
		} catch (Exception e) {
			Assert.fail("Fail to insert");
		}

		Assert.assertNotNull("id problem", d.getId());

		try {
			d = dao.findById(d.getId());
			if (d == null) {
				Assert.fail("entity not found");
			}
		} catch (Exception e) {
			Assert.fail("Fail find");
		}

		try {
			d.setName("DepartmentTest2");
			d.setDescription("DepartmentTest2");
			dao.insertOrUpdate(d);
		} catch (Exception e) {
			Assert.fail("Fail update");
		}

		// delete
		try {
			dao.delete(d.getId());
		} catch (Exception e) {
			Assert.fail("Fail delete");
		}
	}
}
