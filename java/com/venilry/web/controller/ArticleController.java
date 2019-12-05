package com.venilry.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Article_backup;
import com.venilry.web.pojo.Category;
import com.venilry.web.pojo.User;
import com.venilry.web.pojo.User_backup;
import com.venilry.web.service.impl.ArticleServiceImpl;
import com.venilry.web.service.impl.Article_backupServiceImpl;
import com.venilry.web.service.impl.CommentServiceImpl;
import com.venilry.web.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/articles")
public class ArticleController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ArticleServiceImpl articleServiceImpl;
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private Article_backupServiceImpl article_backupServiceImpl;

	@GetMapping
	@ResponseBody
	public List<Article> getArticles(@RequestParam(defaultValue = "1") Integer categoryId,
			@RequestParam(defaultValue = "99999999") Integer size, @RequestParam(defaultValue = "1") Integer page) {
		return articleServiceImpl.findAllByCategoryIdAndPage(categoryId, size, page);
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer getArticlesCount(@RequestParam Integer categoryId) {
		Integer number = articleServiceImpl.findTheCountByCategoryId(categoryId);
		return number;
	}

	@GetMapping("/{articleId:[0-9]*}")
	@ResponseBody
	public Article getArticle(@PathVariable Integer articleId, Model model) {
		return  articleServiceImpl.findById(articleId);
	}

	@PostMapping
	public String postArticles(Authentication authentication, Integer categoryId, String title, String content) {
		if(title==null||title.equals("")||content==null||content.equals("")) {
			throw new RuntimeException("帖子的标题或内容均不可为空");
		}
		String authenticationUsername = ((org.springframework.security.core.userdetails.User) authentication
				.getPrincipal()).getUsername();
		Integer authenticationId = userServiceImpl.findByUsername(authenticationUsername).getId();
		Article article = new Article();
		article.setUser_id(authenticationId);
		article.setCategory_id(categoryId);
		article.setTitle(title);
		article.setContent_complete(content);
		Article article2 = articleServiceImpl.insert(article);
		return "redirect:/article?articleId=" + article2.getId();
	}

	@DeleteMapping("/{articleId:[0-9]*}")
	@ResponseBody
	public void deleteUser(@PathVariable Integer articleId, Authentication authentication) {
		// 获取管理员信息
		String authenticationUsername = ((org.springframework.security.core.userdetails.User) authentication
				.getPrincipal()).getUsername();
		Integer authenticationId = userServiceImpl.findByUsername(authenticationUsername).getId();
		// 获取想删除的信息
		Article article = articleServiceImpl.findById(articleId);
		// 构建并保存备份信息
		Article_backup article_backup = new Article_backup();
		article_backup.setArticle_id(article.getId());
		article_backup.setCategory_id(article.getCategory_id());
		article_backup.setUser_id(article.getUser_id());
		article_backup.setUser_nickname(article.getUser_nickname());
		article_backup.setCreate_time(article.getCreate_time());
		article_backup.setTitle(article.getTitle());
		article_backup.setContent_synopsis(article.getContent_synopsis());
		article_backup.setContent_complete(article.getContent_complete());
		article_backup.setComment_count(article.getComment_count());
		article_backup.setDelete_user_id(authenticationId);
		article_backup.setDelete_time(new Timestamp(new Date().getTime()));
		article_backupServiceImpl.save(article_backup);
		// 从原表中删除信息
		articleServiceImpl.deleteById(articleId);
	}
}
