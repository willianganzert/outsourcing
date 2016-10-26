package br.com.webapp.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.webapp.dao.PermissionDao;
import br.com.webapp.dao.UserDao;
import br.com.webapp.model.Permission;
import br.com.webapp.model.User;

@ManagedBean(name = "permissionsBean")
@SessionScoped
public class PermissionsBean {

	private List<Permission> listPermissions;

	private List<User> listUsers;
	private List<User> listUsersSelect;

	private Permission permission;

	@PostConstruct
	public void init() {
		permission = new Permission();
		PermissionDao dao = new PermissionDao();
		listPermissions = dao.findAll();
	}

	public void delete(Integer id) {
		PermissionDao dao = new PermissionDao();
		try {
			dao.delete(id);
			listPermissions = dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String save() {
		PermissionDao dao = new PermissionDao();
		try {
			permission.setUsers(new HashSet<User>());
			permission.getUsers().addAll(listUsersSelect);
			dao.insertOrUpdate(permission);
			listPermissions = dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
		return "list-permission";
	}

	public String insert() {
		permission = new Permission();
		listUsersSelect = new ArrayList<>();

		UserDao userDao = new UserDao();
		listUsers = userDao.findAll();

		return "insert-permission";
		
	}
	
	public String update(Integer id) {
		PermissionDao dao = new PermissionDao();
		try {
			UserDao userDao = new UserDao();
			listUsers = userDao.findAll();

			permission = dao.findById(id);
			listUsersSelect = new ArrayList<>();
			for (User u : permission.getUsers()) {
				listUsersSelect.add(new User(u.getId(), u.getName(), u.getDescription()));
			}
		} catch (Exception e) {
		}
		
		return "insert-permission";
	}

	public List<Permission> getListPermissions() {
		return listPermissions;
	}

	public void setListPermissions(List<Permission> listPermissions) {
		this.listPermissions = listPermissions;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public List<User> getListUsersSelect() {
		return listUsersSelect;
	}

	public void setListUsersSelect(List<User> listUsersSelect) {
		this.listUsersSelect = listUsersSelect;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

}
