package com.venilry.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.venilry.web.dao.UserRepository;
import com.venilry.web.pojo.Role;
import com.venilry.web.pojo.User_role;
import com.venilry.web.service.impl.RoleServiceImpl;
import com.venilry.web.service.impl.UserServiceImpl;
import com.venilry.web.service.impl.User_roleServiceImpl;

@Component
public class MyUserDetailsService implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private User_roleServiceImpl user_roleServiceImpl;
	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("用户名为[" + username + "]的用户正在进行身份认证");
		// 获取用户的基本信息
		com.venilry.web.pojo.User user = userServiceImpl.findByUsername(username);
		// 获取用户的角色信息
		ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
		for (Integer roleId : user_roleServiceImpl.findAllRoleIdByUserId(user.getId())) {
			authorityList.add(new SimpleGrantedAuthority("ROLE_" + roleServiceImpl.findNameById(roleId)));
		}
		// 构建包装了用户信息的对象并返回
		return new User(username, passwordEncoder.encode(user.getPassword()), authorityList);
	}

}
