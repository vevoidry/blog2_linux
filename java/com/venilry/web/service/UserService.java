package com.venilry.web.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.venilry.web.pojo.User;

public interface UserService {
	List<User> findAll();

	User findById(Integer id);

	User save(User user);

	User findByUsername(String username);

	String findNicknameById(Integer id);

	List<User> findAllByPage(Integer size, Integer page);

	Integer findTheCount();

	User insert(User user);
	
	void deleteById(Integer id);
	
	User findByNickname(String nickname);
}
