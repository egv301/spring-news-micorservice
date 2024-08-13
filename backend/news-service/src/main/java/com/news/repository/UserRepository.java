package com.news.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
