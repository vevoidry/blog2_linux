package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Article_backup;
import com.venilry.web.pojo.User_backup;

public interface Article_backupRepository extends JpaRepository<Article_backup, Integer> {
	@Query(value = "select count(id) from article_backup", nativeQuery = true)
	Integer findTheCount();
	
	@Query(value = "select * from article_backup limit :nonWantedSize,:wantedSize", nativeQuery = true)
	List<Article_backup> findAllByPage(Integer nonWantedSize, Integer wantedSize);
	
	@Query(value = "select count(id) from article_backup where category_id = :category_id", nativeQuery = true)
	Integer findTheCountByCategoryId(Integer category_id);
	
	@Query(value = "select * from article_backup where category_id = :category_id limit :nonWantedSize,:wantedSize", nativeQuery = true)
	List<Article_backup> findAllByCategoryIdAndPage(Integer category_id, Integer nonWantedSize, Integer wantedSize);
}
