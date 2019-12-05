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

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Article_backup;
import com.venilry.web.service.impl.ArticleServiceImpl;
import com.venilry.web.service.impl.Article_backupServiceImpl;

@Controller
@RequestMapping("/article_backups")
public class Article_backupController {

	@Autowired
	private Article_backupServiceImpl article_backupServiceImpl;
	@Autowired
	private ArticleServiceImpl articleServiceImpl;

	@GetMapping
	@ResponseBody
	public List<Article_backup> getArticle_backups(@RequestParam(defaultValue = "1") Integer categoryId,
			@RequestParam(defaultValue = "99999999") Integer size, @RequestParam(defaultValue = "1") Integer page) {
		return article_backupServiceImpl.findAllByCategoryIdAndPage(categoryId, size, page);
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer getArticle_backupsCount(@PathVariable Integer categoryId) {
		return article_backupServiceImpl.findTheCountByCategoryId(categoryId);
	}

	@DeleteMapping("/{article_backupId:[0-9]*}")
	@ResponseBody
	public void deleteUser(@PathVariable Integer article_backupId, Authentication authentication) {
		// 获取想恢复的信息
		Article_backup article_backup = article_backupServiceImpl.findById(article_backupId);
		// 构建并恢复信息
		Article article = new Article();
		// 注意：此处的setId其实是无效的，因为插入新数据时，id会自动生成
		article.setId(article_backup.getArticle_id());
		article.setCategory_id(article_backup.getCategory_id());
		article.setUser_id(article_backup.getUser_id());
		article.setUser_nickname(article_backup.getUser_nickname());
		article.setCreate_time(article_backup.getCreate_time());
		article.setTitle(article_backup.getTitle());
		article.setContent_synopsis(article_backup.getContent_synopsis());
		article.setContent_complete(article_backup.getContent_complete());
		article.setComment_count(article_backup.getComment_count());
		articleServiceImpl.save(article);
		// 从备份表中删除信息
		article_backupServiceImpl.deleteById(article_backupId);
	}

}
