package com.venilry.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Category_backup;

public interface Category_backupRepository extends JpaRepository<Category_backup, Integer> {

}
