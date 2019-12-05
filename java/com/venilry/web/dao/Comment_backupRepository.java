package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Comment;
import com.venilry.web.pojo.Comment_backup;

public interface Comment_backupRepository extends JpaRepository<Comment_backup, Integer> {

	@Query(value = "select count(id) from comment_backup", nativeQuery = true)
	Integer findTheCount();
	
	@Query(value = "select * from comment_backup limit :nonWantedSize,:wantedSize", nativeQuery = true)
	List<Comment_backup> findAllByPage(Integer nonWantedSize, Integer wantedSize);
}
