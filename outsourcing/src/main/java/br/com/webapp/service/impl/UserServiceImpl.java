package br.com.webapp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.webapp.model.User;
import br.com.webapp.repository.UserRepository;
import br.com.webapp.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserRepository userRepository;

	@Override
	@Transactional
	public User create(User user) {
		User createdUser = user;
		return userRepository.save(createdUser);
	}

	@Override
	@Transactional
	public User findById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional
	public User delete(int id) {
		User deletedUser = userRepository.findOne(id);

		userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User update(User user) {
		User updatedUser = userRepository.findOne(user.getId());

		updatedUser.setName(user.getName());
		updatedUser.setDescription(user.getDescription());
		return updatedUser;
	}

	@Override
	public boolean isUserExist(User user) {
		return userRepository.findOne(user.getId()) != null;
	}
}
