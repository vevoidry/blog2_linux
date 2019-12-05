package com.venilry.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.venilry.web.pojo.User;
import com.venilry.web.pojo.User_backup;
import com.venilry.web.service.impl.UserServiceImpl;
import com.venilry.web.service.impl.User_backupServiceImpl;

@Controller
@RequestMapping("/user_backups")
public class User_backupController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private User_backupServiceImpl user_backupServiceImpl;

	@GetMapping
	@ResponseBody
	public List<User_backup> getUser_backups(@RequestParam(defaultValue = "99999999") Integer size,
			@RequestParam(defaultValue = "1") Integer page) {
		List<User_backup> user_backupList = user_backupServiceImpl.findAllByPage(size, page);
		// 使密码不可见
		for (User_backup user_backup : user_backupList) {
			user_backup.setPassword(null);
		}
		return user_backupList;
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer getUser_backupsCount() {
		return user_backupServiceImpl.findTheCount();
	}

	@DeleteMapping("/{user_backupId:[0-9]*}")
	@ResponseBody
	public void deleteUser_backup(@PathVariable Integer user_backupId, Authentication authentication) {
		// 获取想恢复的信息
		User_backup user_backup = user_backupServiceImpl.findById(user_backupId);
		// 构建并恢复信息
		User user = new User();
		// 注意：此处的setId其实是无效的，因为插入新数据时，id会自动生成
		user.setId(user_backup.getUser_id());
		user.setUsername(user_backup.getUsername());
		user.setPassword(user_backup.getPassword());
		user.setNickname(user_backup.getNickname());
		user.setImage(user_backup.getImage());
		user.setGender(user_backup.getGender());
		user.setBirthday(user_backup.getBirthday());
		user.setProfile(user_backup.getProfile());
		User user2 = userServiceImpl.save(user);
		// 从备份表中删除信息
		user_backupServiceImpl.deleteById(user_backupId);
	}

}
