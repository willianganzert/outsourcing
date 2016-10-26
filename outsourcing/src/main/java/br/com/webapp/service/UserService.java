package br.com.webapp.service;

import java.util.List;

import br.com.webapp.model.User;

public interface UserService {

	public User create(User user);

	public User delete(int id);

	public List<User> findAll();

	public User update(User user);

	public User findById(int id);

	public boolean isUserExist(User user);
}
