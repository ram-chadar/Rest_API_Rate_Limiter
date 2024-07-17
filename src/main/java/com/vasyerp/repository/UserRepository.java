package com.vasyerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasyerp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	 User findByApiKey(String apiKey);
}
