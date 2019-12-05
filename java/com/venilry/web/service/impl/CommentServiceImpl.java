package com.venilry.web.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.venilry.web.dao.ArticleRepository;
import com.venilry.web.dao.CommentRepository;
import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Comment;
import com.venilry.web.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private ArticleServiceImpl articleServiceImpl;

	@Override
	public Integer findTheCountByArticleId(Integer article_id) {
		return commentRepository.findTheCountByArticleId(article_id);
	}

	@Override
	public List<Comment> findAllByArticleIdAndPage(Integer article_id, Integer size, Integer page) {
		return commentRepository.findAllByArticleIdAndPage(article_id, (page - 1) * size, size);
	}

	@Override
	public Comment insert(Comment comment) {
		// 插入一条comment数据
		comment.setUser_nickname(userServiceImpl.findNicknameById(comment.getUser_id()));
		comment.setCreate_time(new Timestamp(new Date().getTime()));
		comment.setComment_rank(articleServiceImpl.findCommentCountById(comment.getArticle_id()) + 1);
		Comment comment2 = commentRepository.save(comment);
		// 更新article的comment_count信息
		Integer comment_rank = comment2.getComment_rank();
		Integer article_id = comment2.getArticle_id();
		Article article = articleServiceImpl.findById(article_id);
		article.setComment_count(comment_rank);
		articleServiceImpl.save(article);
		return comment2;
	}

	@Override
	public Integer updateAllUserNicknameByUserId(Integer user_Id, String user_nickname) {
		return commentRepository.updateAllUserNicknameByUserId(user_Id, user_nickname);
	}

	@Override
	public Integer findTheCount() {
		return commentRepository.findTheCount();
	}

	@Override
	public List<Comment> findAllByPage(Integer size, Integer page) {
		return commentRepository.findAllByPage((page-1)*size, size);
	}

	@Override
	public Comment findById(Integer id) {
		return commentRepository.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		commentRepository.deleteById(id);
	}

	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

}
