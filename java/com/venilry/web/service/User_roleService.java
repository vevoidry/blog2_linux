package com.venilry.web.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.venilry.web.pojo.User_role;

public interface User_roleService {
	List<Integer> findAllRoleIdByUserId(Integer user_id);

	User_role save(User_role user_role);
}
