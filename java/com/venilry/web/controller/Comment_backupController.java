package com.venilry.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.venilry.web.pojo.Comment;
import com.venilry.web.pojo.Comment_backup;
import com.venilry.web.service.impl.CommentServiceImpl;
import com.venilry.web.service.impl.Comment_backupServiceImpl;

@Controller
@RequestMapping("/comment_backups")
public class Comment_backupController {
	@Autowired
	private Comment_backupServiceImpl comment_backupServiceImpl;
	@Autowired
	private CommentServiceImpl commentServiceImpl;

	@GetMapping
	@ResponseBody
	public List<Comment_backup> getComment_backups(@RequestParam(defaultValue = "99999999") Integer size,
			@RequestParam(defaultValue = "1") Integer page) {
		return comment_backupServiceImpl.findAllByPage(size, page);
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer getComment_backupsCount() {
		return comment_backupServiceImpl.findTheCount();
	}

	@DeleteMapping("/{id:[0-9]*}")
	@ResponseBody
	public void deleteComment_backup(@PathVariable Integer id) {
		// 获取想恢复的信息
		Comment_backup comment_backup = comment_backupServiceImpl.findById(id);
		// 构建并保存信息
		Comment comment = new Comment();
		comment.setId(comment_backup.getComment_id());
		comment.setUser_id(comment_backup.getUser_id());
		comment.setUser_nickname(comment_backup.getUser_nickname());
		comment.setArticle_id(comment_backup.getArticle_id());
		comment.setCreate_time(comment_backup.getCreate_time());
		comment.setComment_rank(comment_backup.getComment_rank());
		comment.setContent(comment_backup.getContent());
		commentServiceImpl.save(comment);
		// 从备份表删除信息
		comment_backupServiceImpl.deleteById(id);
	}

}
