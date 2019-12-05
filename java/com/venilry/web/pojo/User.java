package com.venilry.web.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "userIdGenerator")
	@GenericGenerator(name = "userIdGenerator", strategy = "com.venilry.web.pojo.generator.UserIdGenerator")
	@Column(name = "id")
	private Integer id;
	@Pattern(regexp = "[0-9a-zA-Z]{6,15}")
	@Column(name = "username")
	private String username;
	@Pattern(regexp = "[0-9a-zA-Z]{6,15}")
	@Column(name = "password")
	private String password;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "image")
	private String image;
	@Column(name = "gender")
	private String gender;
	@Column(name = "birthday")
	private Date birthday;
	@Column(name = "profile")
	private String profile;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
}
