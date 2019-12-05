package com.venilry.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venilry.web.dao.Category_backupRepository;
import com.venilry.web.pojo.Category_backup;
import com.venilry.web.service.Category_backupService;

@Service
public class Category_backupServiceImpl implements Category_backupService {
	@Autowired
	private Category_backupRepository category_backupRepository;

	@Override
	public List<Category_backup> findAll() {
		return category_backupRepository.findAll();
	}

	@Override
	public Category_backup insert(Category_backup category_backup) {
		return category_backupRepository.save(category_backup);
	}

	@Override
	public Category_backup findById(Integer id) {
		return category_backupRepository.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		category_backupRepository.deleteById(id);
	}

}
