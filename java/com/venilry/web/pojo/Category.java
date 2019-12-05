package com.venilry.web.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "category")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "categoryIdGenerator")
	@GenericGenerator(name = "categoryIdGenerator", strategy = "com.venilry.web.pojo.generator.CategoryIdGenerator")
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
