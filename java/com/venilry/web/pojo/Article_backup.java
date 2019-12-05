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
@Table(name = "article_backup")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Article_backup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "article_id")
	private Integer article_id;
	@Column(name = "category_id")
	private Integer category_id;
	@Column(name = "user_id")
	private Integer user_id;
	@Column(name = "user_nickname")
	private String user_nickname;
	@Column(name = "create_time")
	private Timestamp create_time;
	@Column(name = "title")
	private String title;
	@Column(name = "content_synopsis")
	private String content_synopsis;
	@Column(name = "content_complete")
	private String content_complete;
	@Column(name = "comment_count")
	private Integer comment_count;
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
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent_synopsis() {
		return content_synopsis;
	}
	public void setContent_synopsis(String content_synopsis) {
		this.content_synopsis = content_synopsis;
	}
	public String getContent_complete() {
		return content_complete;
	}
	public void setContent_complete(String content_complete) {
		this.content_complete = content_complete;
	}
	public Integer getComment_count() {
		return comment_count;
	}
	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
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
