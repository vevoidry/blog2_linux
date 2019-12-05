package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.venilry.web.pojo.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Query(value = "select count(id) from article where category_id = :category_id", nativeQuery = true)
	Integer findTheCountByCategoryId(Integer category_id);

	@Query(value = "select * from article where category_id = :category_id limit :nonWantedSize,:wantedSize", nativeQuery = true)
	List<Article> findAllByCategoryIdAndPage(Integer category_id, Integer nonWantedSize, Integer wantedSize);

	@Query(value = "select comment_count from article where id=:id", nativeQuery = true)
	Integer findCommentCountById(Integer id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update article set user_nickname=:user_nickname where user_id=:user_id", nativeQuery = true)
	Integer updateAllUserNicknameByUserId(Integer user_id, String user_nickname);
}
