package com.venilry.web.service;

import java.util.List;

import com.venilry.web.pojo.Article;

public interface ArticleService {
	Article findById(Integer id);

	Article save(Article article);

	List<Article> findAllByCategoryIdAndPage(Integer categoryId, Integer size, Integer page);

	Article insert(Article article);

	Integer findTheCountByCategoryId(Integer category_id);

	Integer findCommentCountById(Integer id);

	Integer updateAllUserNicknameByUserId(Integer user_id, String user_nickname);
	
	void deleteById(Integer id);
}
