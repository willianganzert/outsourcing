package br.com.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
