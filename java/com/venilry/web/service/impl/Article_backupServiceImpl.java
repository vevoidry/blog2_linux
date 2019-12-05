package com.venilry.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venilry.web.dao.Article_backupRepository;
import com.venilry.web.pojo.Article_backup;
import com.venilry.web.service.Article_backupService;

@Service
public class Article_backupServiceImpl implements Article_backupService {
	@Autowired
	private Article_backupRepository article_backupRepository;

	@Override
	public void deleteById(Integer id) {
		article_backupRepository.deleteById(id);
	}

	@Override
	public Article_backup save(Article_backup article_backup) {
		return article_backupRepository.save(article_backup);
	}

	@Override
	public Article_backup findById(Integer id) {
		return article_backupRepository.findById(id).get();
	}

	@Override
	public Integer findTheCount() {
		return article_backupRepository.findTheCount();
	}

	@Override
	public List<Article_backup> findAllByPage(Integer size, Integer page) {
		return article_backupRepository.findAllByPage((page - 1) * size, size);
	}

	@Override
	public Integer findTheCountByCategoryId(Integer category_id) {
		return article_backupRepository.findTheCountByCategoryId(category_id);
	}

	@Override
	public List<Article_backup> findAllByCategoryIdAndPage(Integer categoryId, Integer size, Integer page) {
		return article_backupRepository.findAllByCategoryIdAndPage(categoryId, (page - 1) * size, size);
	}

}
