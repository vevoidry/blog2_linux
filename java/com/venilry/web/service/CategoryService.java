package com.venilry.web.service;

import java.util.List;

import com.venilry.web.pojo.Category;

public interface CategoryService {
	List<Category> findAll();
	
	Category insert(Category category);
	
	Category findById(Integer id);
	
	void deleteById(Integer id);
	
	Category save(Category category);
}
