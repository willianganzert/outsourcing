package br.com.webapp.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.webapp.dao.DepartmentDao;
import br.com.webapp.dao.UserDao;
import br.com.webapp.model.Department;
import br.com.webapp.model.User;

@ManagedBean(name = "usersBean")
@SessionScoped
public class UsersBean {

	private List<User> listUsers;

	private List<Department> listDepartments;

	private User user;

	@PostConstruct
	public void init() {
		user = new User();
		UserDao dao = new UserDao();
		listUsers = dao.findAll();
	}

	public void delete(Integer id) {
		UserDao dao = new UserDao();
		try {
			dao.delete(id);
			listUsers = dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String save() {
		UserDao dao = new UserDao();
		try {
			dao.insertOrUpdate(user);
			listUsers = dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		return "list-user";
	}

	public String insert() {
		user = new User();

		DepartmentDao dao = new DepartmentDao();
		listDepartments = dao.findAll();

		return "insert-user";

	}

	public String update(Integer id) {
		UserDao dao = new UserDao();
		try {
			DepartmentDao departmentDao = new DepartmentDao();
			listDepartments = departmentDao.findAll();

			user = dao.findById(id);
		} catch (Exception e) {
		}

		return "insert-user";
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Department> getListDepartments() {
		return listDepartments;
	}

	public void setListDepartments(List<Department> listDepartments) {
		this.listDepartments = listDepartments;
	}

}
