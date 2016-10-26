package br.com.webapp.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.webapp.dao.DepartmentDao;
import br.com.webapp.model.Department;

@ManagedBean(name = "departmentsBean")
@SessionScoped
public class DepartmentsBean {

	private List<Department> listDepartments;

	private Department department;

	@PostConstruct
	public void init() {
		department = new Department();
		DepartmentDao dao = new DepartmentDao();
		listDepartments = dao.findAll();
	}

	public void delete(Integer id) {
		DepartmentDao dao = new DepartmentDao();
		try {
			Department department = dao.findById(id);
			if (department.getUsers() == null || department.getUsers().isEmpty()) {
				dao.delete(id);
			}
			listDepartments = dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String save() {
		DepartmentDao dao = new DepartmentDao();
		try {
			dao.insertOrUpdate(department);
			listDepartments = dao.findAll();
		} catch (Exception e) {
			return "";
		}
		
		return "list-department";
	}

	public String insert() {
		department = new Department();
		
		return "insert-department";
		
	}
	
	public String update(Integer id) {
		DepartmentDao dao = new DepartmentDao();
		try {
			department = dao.findById(id);
		} catch (Exception e) {
		}
		
		return "insert-department";
	}

	public List<Department> getListDepartments() {
		return listDepartments;
	}

	public void setListDepartments(List<Department> listDepartments) {
		this.listDepartments = listDepartments;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
