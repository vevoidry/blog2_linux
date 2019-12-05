package com.venilry.web.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comment")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "commentIdGenerator")
	@GenericGenerator(name = "commentIdGenerator", strategy = "com.venilry.web.pojo.generator.CommentIdGenerator")
	@Column(name = "id")
	private Integer id;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
}
