package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "select * from user where username = :username", nativeQuery = true)
	User findByUsername(String username);

	@Query(value = "select nickname from user where id = :id", nativeQuery = true)
	String findNicknameById(Integer id);

	@Query(value = "select * from user limit :nonWantedSize,:wantedSize", nativeQuery = true)
	List<User> findAllByPage(Integer nonWantedSize, Integer wantedSize);

	@Query(value = "select count(id) from user", nativeQuery = true)
	Integer findTheCount();
	
	@Query(value = "select * from user where nickname = :nickname", nativeQuery = true)
	User findByNickname(String nickname);
	
}
