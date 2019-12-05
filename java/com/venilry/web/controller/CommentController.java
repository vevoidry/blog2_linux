package com.venilry.web.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.venilry.web.pojo.Article;
import com.venilry.web.pojo.Category;
import com.venilry.web.pojo.Comment;
import com.venilry.web.pojo.Comment_backup;
import com.venilry.web.pojo.User;
import com.venilry.web.pojo.User_backup;
import com.venilry.web.service.impl.ArticleServiceImpl;
import com.venilry.web.service.impl.CommentServiceImpl;
import com.venilry.web.service.impl.Comment_backupServiceImpl;
import com.venilry.web.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private Comment_backupServiceImpl comment_backupServiceImpl;

	@GetMapping
	@ResponseBody
	public List<Comment> getComments(@RequestParam Integer articleId,
			@RequestParam(defaultValue = "99999999") Integer size, @RequestParam(defaultValue = "1") Integer page) {
		if (articleId == null) {
			return commentServiceImpl.findAllByPage(size, page);
		}
		List<Comment> commentList = commentServiceImpl.findAllByArticleIdAndPage(articleId, size, page);
		return commentList;
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer getCommentsCount(@RequestParam Integer articleId) {
		if (articleId == null) {
			return commentServiceImpl.findTheCount();
		}
		return commentServiceImpl.findTheCountByArticleId(articleId);
	}

	@PostMapping
	public String postComments(@RequestParam Integer articleId,
			@RequestParam String content,Authentication authentication) {
		if(content==null||content.equals("")) {
			throw new RuntimeException("评论内容不可为空");
		}
		String authenticationUsername = ((org.springframework.security.core.userdetails.User) authentication
				.getPrincipal()).getUsername();
		Integer authenticationId = userServiceImpl.findByUsername(authenticationUsername).getId();
		Comment comment = new Comment();
		comment.setUser_id(authenticationId);
		comment.setArticle_id(articleId);
		comment.setContent(content);
		commentServiceImpl.insert(comment);
		return "redirect:/article?articleId=" + articleId;
	}

	@DeleteMapping("/{id:[0-9]*}")
	@ResponseBody
	public void deleteComment(@PathVariable Integer id, Authentication authentication) {
		// 获取管理员信息
		String authenticationUsername = ((org.springframework.security.core.userdetails.User) authentication
				.getPrincipal()).getUsername();
		Integer authenticationId = userServiceImpl.findByUsername(authenticationUsername).getId();
		// 获取想删除的信息
		Comment comment = commentServiceImpl.findById(id);
		// 构建并保存备份信息
		Comment_backup comment_backup = new Comment_backup();
		comment_backup.setComment_id(comment.getId());
		comment_backup.setUser_id(comment.getUser_id());
		comment_backup.setUser_nickname(comment.getUser_nickname());
		comment_backup.setArticle_id(comment.getArticle_id());
		comment_backup.setCreate_time(comment.getCreate_time());
		comment_backup.setComment_rank(comment.getComment_rank());
		comment_backup.setContent(comment.getContent());
		comment_backup.setDelete_user_id(authenticationId);
		comment_backup.setDelete_time(new Timestamp(new Date().getTime()));
		comment_backupServiceImpl.save(comment_backup);
		// 从原表中删除信息
		commentServiceImpl.deleteById(id);
	}

}
