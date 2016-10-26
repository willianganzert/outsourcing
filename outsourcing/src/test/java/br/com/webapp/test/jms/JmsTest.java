package br.com.webapp.test.jms;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.webapp.dao.DepartmentDao;
import br.com.webapp.init.Application;
import br.com.webapp.jms.MessageSender;
import br.com.webapp.model.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class JmsTest {

	@Autowired
	private MessageSender sender;

	@Test
	public void jmsTest() {
		String name = "NEW DEPARTMENTTEST2-"+UUID.randomUUID();
		Department d = new Department(null, name, "NEW DEPARTMENT2");
		sender.sendMessage(d);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Assert.fail("Fail application during the test.");
		}

		DepartmentDao dao = new DepartmentDao();
		List<Department> departments = dao.findByName(name);
		boolean found = false;
		for (Department department : departments) {
			if(department.getName().equals(name)){
				found = true;break;
			}
		}
		
		if(!found){
			Assert.fail("JMS persistence fail.");
		}
		else{
			dao.delete(departments.get(0).getId());
		}
		
	}
}