package com.vasyerp.service;

import java.util.List;

import com.vasyerp.entity.User;

public interface UserService {

	public String createUser(User user);

	public User getUserById(Long id);

	public List<User> getAllUsers();

	public User updateUser(Long id, User user);

	public String deleteUser(Long id);
}
