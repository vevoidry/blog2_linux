package com.venilry.web.service;

import java.util.List;

import com.venilry.web.pojo.Comment;
import com.venilry.web.pojo.Comment_backup;

public interface Comment_backupService {
	
	Comment_backup save(Comment_backup comment_backup);
	
	Integer findTheCount();
	
	List<Comment_backup> findAllByPage(Integer size, Integer page);
	
	Comment_backup findById(Integer id);
	
	void deleteById(Integer id);

}
