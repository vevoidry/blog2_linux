package com.venilry.web.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comment_backup")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Comment_backup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "comment_id")
	private Integer comment_id;
	@Column(name = "user_id")
	private Integer user_id;
	@Column(name = "user_nickname")
	private String user_nickname;
	@Column(name = "article_id")
	private Integer article_id;
	@Column(name = "create_time")
	private Timestamp create_time;
	@Column(name = "comment_rank")
	private Integer comment_rank;
	@Column(name = "content")
	private String content;
	@Column(name = "delete_user_id")
	private Integer delete_user_id;
	@Column(name = "delete_time")
	private Timestamp delete_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Integer getComment_rank() {
		return comment_rank;
	}
	public void setComment_rank(Integer comment_rank) {
		this.comment_rank = comment_rank;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDelete_user_id() {
		return delete_user_id;
	}
	public void setDelete_user_id(Integer delete_user_id) {
		this.delete_user_id = delete_user_id;
	}
	public Timestamp getDelete_time() {
		return delete_time;
	}
	public void setDelete_time(Timestamp delete_time) {
		this.delete_time = delete_time;
	}
}
