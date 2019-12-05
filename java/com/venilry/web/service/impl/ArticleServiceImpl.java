package com.venilry.web.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.venilry.web.dao.ArticleRepository;
import com.venilry.web.dao.CategoryRepository;
import com.venilry.web.pojo.Article;
import com.venilry.web.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public Article findById(Integer id) {
		return articleRepository.findById(id).get();
	}

	@Override
	public Article save(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public List<Article> findAllByCategoryIdAndPage(Integer categoryId, Integer size, Integer page) {
		return articleRepository.findAllByCategoryIdAndPage(categoryId, (page - 1) * size, size);
	}

	@Override
	public Article insert(Article article) {
		String content_complete = article.getContent_complete();
		String content_synopsis = "";
		// 根据帖子内容提取出内容简述
		if (content_complete.length() > 20) {
			content_synopsis = content_complete.substring(0,20);
		} else {
			content_synopsis = content_complete;
		}
		article.setContent_synopsis(content_synopsis);
		article.setCreate_time(new Timestamp(new Date().getTime()));
		article.setComment_count(1);
		article.setUser_nickname(userServiceImpl.findNicknameById(article.getUser_id()));
		return articleRepository.save(article);
	}

	@Override
	public Integer findTheCountByCategoryId(Integer category_id) {
		return articleRepository.findTheCountByCategoryId(category_id);
	}

	@Override
	public Integer findCommentCountById(Integer id) {
		return articleRepository.findCommentCountById(id);
	}

	@Override
	public Integer updateAllUserNicknameByUserId(Integer user_id, String user_nickname) {
		return articleRepository.updateAllUserNicknameByUserId(user_id, user_nickname);
	}

	@Override
	public void deleteById(Integer id) {
		articleRepository.deleteById(id);
	}

}
