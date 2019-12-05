package com.venilry.web.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venilry.web.dao.RoleRepository;
import com.venilry.web.pojo.Role;
import com.venilry.web.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public String findNameById(Integer id) {
		return roleRepository.findNameById(id);
	}
}
