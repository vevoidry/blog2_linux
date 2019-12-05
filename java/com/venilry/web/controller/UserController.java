package com.venilry.web.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.venilry.security.util.HttpServletRequestPost;
import com.venilry.web.dao.ArticleRepository;
import com.venilry.web.dao.CommentRepository;
import com.venilry.web.pojo.Category;
import com.venilry.web.pojo.User;
import com.venilry.web.pojo.User_backup;
import com.venilry.web.pojo.User_role;
import com.venilry.web.service.impl.ArticleServiceImpl;
import com.venilry.web.service.impl.CommentServiceImpl;
import com.venilry.web.service.impl.UserServiceImpl;
import com.venilry.web.service.impl.User_backupServiceImpl;
import com.venilry.web.service.impl.User_roleServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private User_roleServiceImpl user_roleServiceImpl;
	@Autowired
	private User_backupServiceImpl user_backupServiceImpl;
	@Autowired
	private ArticleServiceImpl articleServiceImpl;
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	@Autowired
	private String staticPath;

	@GetMapping
	@ResponseBody
	public List<User> getUsers(@RequestParam(defaultValue = "99999999") Integer size,
			@RequestParam(defaultValue = "1") Integer page) {
		List<User> userList = userServiceImpl.findAllByPage(size, page);
		// 使密码不可见
		for (User user : userList) {
			user.setPassword(null);
		}
		return userList;
	}

	@GetMapping("/count")
	@ResponseBody
	public Integer getUsersCount() {
		return userServiceImpl.findTheCount();
	}

	@GetMapping("/{userId:[0-9]*}")
	@ResponseBody
	public User getUser(@PathVariable Integer userId) {
		User user = userServiceImpl.findById(userId);
		// 使密码不可见
		user.setPassword(null);
		return user;
	}

	@PostMapping
	public void postUsers(@Validated User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (result.hasErrors()) {
			throw new RuntimeException("用户名或密码格式不正确");
		}
		String username = user.getUsername();
		String password = user.getPassword();
		User theUser = new User();
		theUser.setUsername(username);
		theUser.setPassword(password);
		User newUser = userServiceImpl.insert(theUser);
		// 添加角色
		Integer userId = newUser.getId();
		User_role user_role = new User_role();
		user_role.setUser_id(userId);
		user_role.setRole_id(3);
		user_roleServiceImpl.save(user_role);
		// 重新登录（应该有更好的基于spring security的实现方式才对，此次暂且不表）
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", newUser.getUsername());
		parameters.put("password", newUser.getPassword());
		String url = request.getRequestURL().toString().split(request.getRequestURI())[0]
				+ "/authentication/authenticate";
		HttpServletRequestPost.authenticationAuthenticate(response, url, parameters);
	}

	@PostMapping("/{userId:[0-9]*}/image")
	@ResponseBody
	public void postUserImage(Authentication authentication, MultipartFile filename, @PathVariable Integer userId,
			HttpServletResponse response, HttpServletRequest request) throws Exception {// 变量名与前端中form下file的name相同，或通过某个注解进行转换
		if ((filename.getOriginalFilename()).equals("")) {// 不能用==null进行判断
			throw new RuntimeException("您上传的头像有问题");
		}
		org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();
		User user = userServiceImpl.findByUsername(userDetails.getUsername());
		// 判断访问的用户信息是否属于已登录用户本身
		if (userId == user.getId()) {
			// 保存图片至配置的静态资源文件夹中（将自动产生的file:字符去掉）
			String directoryPath = (staticPath + File.separator + "image" + File.separator).substring(5);
			String imageName = UUID.randomUUID().toString().replaceAll("-", "") + filename.getOriginalFilename();
			File file = new File(directoryPath + imageName);
			if (!file.exists()) {
				file.createNewFile();
			}
			filename.transferTo(file);
			// 将图片设置为头像
			User theUser = userServiceImpl.findById(userId);
			theUser.setImage(imageName);
			userServiceImpl.save(theUser);
		}
		// 重新登录
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", user.getUsername());
		parameters.put("password", user.getPassword());
		String url = request.getRequestURL().toString().split(request.getRequestURI())[0]
				+ "/authentication/authenticate";
		HttpServletRequestPost.authenticationAuthenticate(response, url, parameters);
	}

	@PutMapping("/{userId:[0-9]*}")
	@Transactional
	public void putUser(User user, HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		Integer authenticationId = userServiceImpl.findByUsername(
				((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername())
				.getId();
		Integer userId = user.getId();
		// 判断是否是本人
		if (authenticationId != userId) {
			throw new RuntimeException("you have no authority to do this!");
		}
		String nickname = user.getNickname();
		// 取出user原数据和原昵称和原密码
		User originUser = userServiceImpl.findById(userId);
		String originNickname = originUser.getNickname();
		// 判断是否有修改昵称
		if (!nickname.equals(originNickname)) {
			// 判断新昵称是否已被使用
			User othersUser = userServiceImpl.findByNickname(nickname);
			if (othersUser != null) {
				throw new RuntimeException("someone already use the nickname,please change the new nickname");
			} else {// 新昵称未被使用
				// 保存原头像和密码
				user.setImage(originUser.getImage());
				user.setPassword(originUser.getPassword());
				// 保存新user
				User newUser = userServiceImpl.save(user);
				// 更新帖子中的用户名
				articleServiceImpl.updateAllUserNicknameByUserId(newUser.getId(), newUser.getNickname());
				// 更新评论中的用户名
				commentServiceImpl.updateAllUserNicknameByUserId(newUser.getId(), newUser.getNickname());
				// 重新登录
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("username", newUser.getUsername());
				parameters.put("password", newUser.getPassword());
				String url = request.getRequestURL().toString().split(request.getRequestURI())[0]
						+ "/authentication/authenticate";
				HttpServletRequestPost.authenticationAuthenticate(response, url, parameters);
			}
		}
	}

	@DeleteMapping("/{userId:[0-9]*}")
	@ResponseBody
	public void deleteUser(@PathVariable Integer userId, Authentication authentication) {
		// 获取管理员信息
		String authenticationUsername = ((org.springframework.security.core.userdetails.User) authentication
				.getPrincipal()).getUsername();
		Integer authenticationId = userServiceImpl.findByUsername(authenticationUsername).getId();
		// 获取想删除的信息
		User user = userServiceImpl.findById(userId);
		// 构建并保存备份信息
		User_backup user_backup = new User_backup();
		user_backup.setUser_id(user.getId());
		user_backup.setUsername(user.getUsername());
		user_backup.setPassword(user.getPassword());
		user_backup.setNickname(user.getNickname());
		user_backup.setImage(user.getImage());
		user_backup.setGender(user.getGender());
		user_backup.setBirthday(user.getBirthday());
		user_backup.setProfile(user.getProfile());
		user_backup.setDelete_user_id(authenticationId);
		user_backup.setDelete_time(new Timestamp(new Date().getTime()));
		user_backupServiceImpl.save(user_backup);
		// 从原表中删除信息
		userServiceImpl.deleteById(userId);
	}
}
