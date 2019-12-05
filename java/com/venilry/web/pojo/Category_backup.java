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
@Table(name = "category_backup")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Category_backup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "category_id")
	private Integer category_id;
	@Column(name = "name")
	private String name;
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
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
