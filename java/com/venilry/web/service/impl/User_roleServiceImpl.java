package com.venilry.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venilry.web.dao.User_roleRepository;
import com.venilry.web.pojo.User_role;
import com.venilry.web.service.User_roleService;

@Service
public class User_roleServiceImpl implements User_roleService {
	@Autowired
	private User_roleRepository user_roleRepository;

	@Override
	public List<Integer> findAllRoleIdByUserId(Integer user_id) {
		return user_roleRepository.findAllRoleIdByUserId(user_id);
	}

	@Override
	public User_role save(User_role user_role) {
		return user_roleRepository.save(user_role);
	}

}
