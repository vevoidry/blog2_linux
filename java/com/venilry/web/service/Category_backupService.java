package com.venilry.web.service;

import java.util.List;

import com.venilry.web.pojo.Category_backup;

public interface Category_backupService {
	
	List<Category_backup> findAll();
	
	Category_backup insert(Category_backup category_backup);
	
	Category_backup findById(Integer id);
	
	void deleteById(Integer id);

}
