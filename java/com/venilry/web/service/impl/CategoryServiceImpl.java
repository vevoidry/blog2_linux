package com.venilry.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venilry.web.dao.ArticleRepository;
import com.venilry.web.dao.CategoryRepository;
import com.venilry.web.pojo.Category;
import com.venilry.web.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category insert(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findById(Integer id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
}
