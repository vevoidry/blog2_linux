package com.venilry.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.venilry.web.pojo.User;
import com.venilry.web.service.impl.CategoryServiceImpl;
import com.venilry.web.service.impl.UserServiceImpl;

@Controller
public class PageController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserServiceImpl userServiceImpl;
	//
	@GetMapping("/")
	public String main() {
		return "main";
	}
	//
	@GetMapping("/authentication/login")
	public String login() {
		return "login";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/make")
	public String make() {
		return "make";
	}
	//
	@GetMapping("/article")
	public String getArticlePage(@RequestParam Integer articleId, Model model) {
		model.addAttribute("articleId", articleId);
		return "article";
	}
	//
	@GetMapping("/user")
	public String getUserPage(@RequestParam Integer userId, Authentication authentication, Model model) {
		boolean isMe = false;
		boolean isAdmin = false;
		// 判断是否已经登录
		if (authentication != null) {
			org.springframework.security.core.userdetails.User authenticatedUser = (org.springframework.security.core.userdetails.User) authentication
					.getPrincipal();
			String username = authenticatedUser.getUsername();
			User user = userServiceImpl.findByUsername(username);
			// 判断该页面属于登陆者本身
			if (userId == user.getId()) {
				isMe = true;
				// 判断登陆者是否是管理员
				if (authenticatedUser.getAuthorities().toString().contains("ROLE_admin")) {
					isAdmin = true;
				}
			}
		}
		model.addAttribute("userId", userId);
		model.addAttribute("isMe", isMe);
		model.addAttribute("isAdmin", isAdmin);
		return "user";
	}

	@GetMapping("/users/{userId:[0-9]*}/edit")
	public String getUserEdit(@PathVariable Integer userId, Model model) {
		model.addAttribute("userId", userId);
		return "edit";
	}

}
