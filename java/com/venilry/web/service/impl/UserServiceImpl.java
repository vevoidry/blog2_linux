package com.venilry.web.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venilry.web.dao.UserRepository;
import com.venilry.web.pojo.User;
import com.venilry.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public String findNicknameById(Integer id) {
		return userRepository.findNicknameById(id);
	}

	@Override
	public List<User> findAllByPage(Integer size, Integer page) {
		return userRepository.findAllByPage((page - 1) * size, size);
	}

	@Override
	public Integer findTheCount() {
		return userRepository.findTheCount();
	}

	@Override
	public User insert(User user) {
		user.setGender("男");
		user.setImage("x.PNG");
		user.setNickname(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setProfile("I am a person");
		user.setBirthday(new java.sql.Date(new Date().getTime()));
		return userRepository.save(user);
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findByNickname(String nickname) {
		return userRepository.findByNickname(nickname);
	}

}
