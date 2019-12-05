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

import com.venilry.web.pojo.Article_backup;
import com.venilry.web.pojo.Category;
import com.venilry.web.pojo.Category_backup;
import com.venilry.web.service.impl.CategoryServiceImpl;
import com.venilry.web.service.impl.Category_backupServiceImpl;

@Controller
@RequestMapping("/category_backups")
public class Category_backupController {
	@Autowired
	private Category_backupServiceImpl category_backupServiceImpl;
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@GetMapping
	@ResponseBody
	public List<Category_backup> getArticle_backups() {
		return category_backupServiceImpl.findAll();
	}

	@DeleteMapping("/{category_backupId:[0-9]*}")
	@ResponseBody
	public void deleteUser(@PathVariable Integer category_backupId, Authentication authentication) {
		// 获取想删除的信息
		Category_backup category_backup = category_backupServiceImpl.findById(category_backupId);
		// 构建并保存备份信息
		Category category = new Category();
		category.setId(category_backup.getCategory_id());
		category.setName(category_backup.getName());
		categoryServiceImpl.save(category);
		// 从原表中删除信息
		category_backupServiceImpl.deleteById(category_backupId);
	}

}
