package com.venilry.web.service;

import java.util.List;

import com.venilry.web.pojo.Comment;

public interface CommentService {
	Integer findTheCountByArticleId(Integer article_id);

	List<Comment> findAllByArticleIdAndPage(Integer article_id, Integer size, Integer page);

	Comment insert(Comment comment);

	Integer updateAllUserNicknameByUserId(Integer user_Id, String user_nickname);
	
	Integer findTheCount();
	
	List<Comment> findAllByPage(Integer size, Integer page);
	
	Comment findById(Integer id);
	
	void deleteById(Integer id);
	
	Comment save(Comment comment);
	
}
