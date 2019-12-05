package com.venilry.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query(value = "select count(id) from comment where article_id = :article_id", nativeQuery = true)
	Integer findTheCountByArticleId(Integer article_id);

	@Query(value = "select * from comment where article_id = :article_id limit :nonWantedSize,:wantedSize", nativeQuery = true)
	List<Comment> findAllByArticleIdAndPage(Integer article_id, Integer nonWantedSize, Integer wantedSize);

	@Modifying(clearAutomatically = true)
	@Query(value = "update comment set user_nickname=:user_nickname where user_Id=:user_Id", nativeQuery = true)
	Integer updateAllUserNicknameByUserId(Integer user_Id, String user_nickname);
	
	@Query(value = "select count(id) from comment", nativeQuery = true)
	Integer findTheCount();
	
	@Query(value = "select * from comment limit :nonWantedSize,:wantedSize", nativeQuery = true)
	List<Comment> findAllByPage(Integer nonWantedSize, Integer wantedSize);
}
