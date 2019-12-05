package com.venilry.web.service;

import java.util.List;

import com.venilry.web.pojo.User;
import com.venilry.web.pojo.User_backup;

public interface User_backupService {
	void deleteById(Integer id);
	
	User_backup save(User_backup user_backup);
	
	User_backup findById(Integer id);
	
	Integer findTheCount();
	
	List<User_backup> findAllByPage(Integer size, Integer page);
}
