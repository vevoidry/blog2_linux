package com.venilry.web.service;

import java.util.List;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Article_backup;
import com.venilry.web.pojo.User_backup;

public interface Article_backupService {
	void deleteById(Integer id);
	
	Article_backup save(Article_backup article_backup);
	
	Article_backup findById(Integer id);
	
	Integer findTheCount();
	
	List<Article_backup> findAllByPage(Integer size, Integer page);
	
	Integer findTheCountByCategoryId(Integer category_id);
	
	List<Article_backup> findAllByCategoryIdAndPage(Integer categoryId, Integer size, Integer page);
}
