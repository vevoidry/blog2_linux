package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.User;
import com.venilry.web.pojo.User_backup;

public interface User_backupRepository extends JpaRepository<User_backup, Integer> {

	@Query(value = "select count(id) from user_backup", nativeQuery = true)
	Integer findTheCount();
	
	@Query(value = "select * from user_backup limit :nonWantedSize,:wantedSize", nativeQuery = true)
	List<User_backup> findAllByPage(Integer nonWantedSize, Integer wantedSize);
	
}
