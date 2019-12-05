package com.venilry.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venilry.web.dao.User_backupRepository;
import com.venilry.web.dao.User_roleRepository;
import com.venilry.web.pojo.User;
import com.venilry.web.pojo.User_backup;
import com.venilry.web.service.User_backupService;

@Service
public class User_backupServiceImpl implements User_backupService {

	@Autowired
	private User_backupRepository user_backupRepository;

	@Override
	public void deleteById(Integer id) {
		user_backupRepository.deleteById(id);
	}

	@Override
	public User_backup save(User_backup user_backup) {
		return user_backupRepository.save(user_backup);
	}

	@Override
	public User_backup findById(Integer id) {
		return user_backupRepository.findById(id).get();
	}

	@Override
	public Integer findTheCount() {
		return user_backupRepository.findTheCount();
	}

	@Override
	public List<User_backup> findAllByPage(Integer size, Integer page) {
		return user_backupRepository.findAllByPage((page - 1) * size, size);
	}

}
