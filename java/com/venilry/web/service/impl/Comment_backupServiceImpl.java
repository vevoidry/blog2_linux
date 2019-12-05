package com.venilry.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venilry.web.dao.Comment_backupRepository;
import com.venilry.web.pojo.Comment;
import com.venilry.web.pojo.Comment_backup;
import com.venilry.web.service.Comment_backupService;

@Service
public class Comment_backupServiceImpl implements Comment_backupService {
	@Autowired
	private Comment_backupRepository comment_backupRepository;

	@Override
	public Comment_backup save(Comment_backup comment_backup) {
		return comment_backupRepository.save(comment_backup);
	}

	@Override
	public Integer findTheCount() {
		return comment_backupRepository.findTheCount();
	}

	@Override
	public List<Comment_backup> findAllByPage(Integer size, Integer page) {
		return comment_backupRepository.findAllByPage((page-1)*size, size);
	}

	@Override
	public Comment_backup findById(Integer id) {
		return comment_backupRepository.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		comment_backupRepository.deleteById(id);
	}

}
