package com.vasyerp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasyerp.entity.User;
import com.vasyerp.exception.CustomizedException;
import com.vasyerp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String createUser(User user) {

		Optional<User> existingUser = userRepository.findById(user.getId());
		if (existingUser.isPresent()) {
			throw new CustomizedException(6L, "User Already present with Id " + user.getId());
		}
		User userEntity = new User(user.getId(), user.getUsername());
		userRepository.save(userEntity);
		return "User Created Successfully";
	}

	@Override
	public User getUserById(Long id) {

		Optional<User> getById = userRepository.findById(id);
		if (!getById.isPresent()) {
			throw new CustomizedException(7L, "user not found with id : " + id);
		}
		return getById.get();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User updateUser(Long id, User user) {

		Optional<User> DBid = userRepository.findById(id);
		if (!DBid.isPresent()) {
			throw new CustomizedException(8L, "User not found with id: " + id);
		}
		User existingUser = DBid.get();
		if (user.getUsername() != null && !user.getUsername().isEmpty()) {
			existingUser.setUsername(user.getUsername());
		} else if (user.getUsername() != null) {
			throw new CustomizedException(9L, "username cannot be null or empty");
		}
		return userRepository.save(existingUser);
	}

	@Override
	public String deleteUser(Long id) {

		Optional<User> idExistInDB = userRepository.findById(id);
		if (idExistInDB.isPresent()) {
			userRepository.deleteById(id);
			return "User deleted successfully";
		} else {
			throw new CustomizedException(10L, "User not found with id: " + id);
		}
	}

}
