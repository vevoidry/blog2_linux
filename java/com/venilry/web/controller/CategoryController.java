package com.venilry.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.venilry.web.dao.ArticleRepository;
import com.venilry.web.dao.CategoryRepository;
import com.venilry.web.dao.CommentRepository;
import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Category;
import com.venilry.web.pojo.Category_backup;
import com.venilry.web.pojo.Comment;
import com.venilry.web.service.impl.ArticleServiceImpl;
import com.venilry.web.service.impl.Article_backupServiceImpl;
import com.venilry.web.service.impl.CategoryServiceImpl;
import com.venilry.web.service.impl.Category_backupServiceImpl;
import com.venilry.web.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/categorys")
public class CategoryController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	@Autowired
	private ArticleServiceImpl articleServiceImpl;
	@Autowired
	private Article_backupServiceImpl article_backupServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private Category_backupServiceImpl category_backupServiceImpl;

	@GetMapping
	@ResponseBody
	public List<Category> getCategorys() {
		return categoryServiceImpl.findAll();
	}

	@PostMapping
	@ResponseBody
	public void postCategorys(@RequestParam String categoryName) {
		if(categoryName==null||categoryName.equals("")) {
			throw new RuntimeException("目录名称不可为空");
		}
		Category category = new Category();
		category.setName(categoryName);
		categoryServiceImpl.insert(category);
	}

	@DeleteMapping("/{categoryId:[0-9]*}")
	@ResponseBody
	public void deleteUser(@PathVariable Integer categoryId, Authentication authentication) {
		// 获取管理员信息
		String authenticationUsername = ((org.springframework.security.core.userdetails.User) authentication
				.getPrincipal()).getUsername();
		Integer authenticationId = userServiceImpl.findByUsername(authenticationUsername).getId();
		// 获取想删除的信息
		Category category = categoryServiceImpl.findById(categoryId);
		// 构建并保存备份信息
		Category_backup category_backup = new Category_backup();
		category_backup.setCategory_id(category.getId());
		category_backup.setName(category.getName());
		category_backup.setDelete_user_id(authenticationId);
		category_backup.setDelete_time(new Timestamp(new Date().getTime()));
		category_backupServiceImpl.insert(category_backup);
		// 从原表中删除信息
		categoryServiceImpl.deleteById(categoryId);
	}

}
