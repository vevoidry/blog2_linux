package com.venilry.security;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Autowired
	private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 与springboot的jar包同级，是该jar所对应的静态资源文件夹
	@Bean(name = "staticPath")
	public String staticPath() {
		File path = null;
		try {
			path = new File(ResourceUtils.getURL("classpath:").getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return path.getParentFile().getParentFile().getParent() + File.separator + "blog2";
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// 授权管理
				// 一旦遇到第一个能匹配的字符串，则直接出结果，不会继续往下走。
				// 所以越具体的应该放越前面？
				// 总结：springsecurity没有仔细地区分权限和角色。可以把角色理解为带着ROLE_前缀的权限，然后只使用hasAuthority用完全字符串即可操作角色和权限。
				// hasRole()里的ROLE_开头的字符串无法通过编译
				.authorizeRequests().regexMatchers("/css/.*", "/image/.*", "/js/.*").permitAll()

				.regexMatchers(HttpMethod.GET, "/article_backups").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.GET, "/article_backups/count").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.DELETE, "/article_backups/[0-9]*").hasAuthority("ROLE_admin")

				.regexMatchers(HttpMethod.GET, "/articles").permitAll().regexMatchers(HttpMethod.GET, "/articles/count")
				.permitAll().regexMatchers(HttpMethod.GET, "/articles/[0-9]*").permitAll()
				.regexMatchers(HttpMethod.POST, "/articles").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.DELETE, "/article_backups/[0-9]*").hasAuthority("ROLE_admin")

				.regexMatchers(HttpMethod.GET, "/category_backups").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.DELETE, "/category_backups/[0-9]*").hasAuthority("ROLE_admin")

				.regexMatchers(HttpMethod.GET, "/categorys").permitAll().regexMatchers(HttpMethod.POST, "/categorys")
				.hasAuthority("ROLE_admin").regexMatchers(HttpMethod.DELETE, "/categorys/[0-9]*")
				.hasAuthority("ROLE_admin")

				.regexMatchers(HttpMethod.GET, "/comment_backups").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.POST, "/comment_backups/count").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.DELETE, "/comment_backups/[0-9]*").hasAuthority("ROLE_admin")

				.regexMatchers(HttpMethod.GET, "/comments").permitAll().regexMatchers(HttpMethod.GET, "/comments/count")
				.permitAll().regexMatchers(HttpMethod.POST, "/comments").authenticated()
				.regexMatchers(HttpMethod.DELETE, "/comments/[0-9]*").hasAuthority("ROLE_admin")

				.regexMatchers(HttpMethod.GET, "/").permitAll().regexMatchers(HttpMethod.GET, "/authentication/login")
				.permitAll().regexMatchers(HttpMethod.GET, "/admin").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.GET, "/make").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.GET, "/article").permitAll().regexMatchers(HttpMethod.GET, "/user")
				.permitAll().regexMatchers(HttpMethod.GET, "/user/[0-9]*/edit").authenticated()

				.regexMatchers(HttpMethod.GET, "/user_backups").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.GET, "/user_backups/count").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.DELETE, "/user_backups/[0-9]*").hasAuthority("ROLE_admin")

				.regexMatchers(HttpMethod.GET, "/users").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.GET, "/users/count").hasAuthority("ROLE_admin")
				.regexMatchers(HttpMethod.GET, "/users/[0-9]*").permitAll().regexMatchers(HttpMethod.POST, "/users")
				.permitAll().regexMatchers(HttpMethod.POST, "/users/[0-9]*/image").authenticated()
				.regexMatchers(HttpMethod.PUT, "/users/[0-9]*").authenticated()
				.regexMatchers(HttpMethod.DELETE, "/users/[0-9]*").hasAuthority("ROLE_admin").anyRequest().permitAll()
				// 认证管理
				.and().formLogin().loginPage("/authentication/login").loginProcessingUrl("/authentication/authenticate")
				.successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler)
				// 退出管理
				.and().logout().logoutUrl("/authentication/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID")
				.and().csrf().disable();
	}
	/*
	 * 静态资源放行
	 */
	/*
	 * @Override public void configure(WebSecurity web) throws Exception { web
	 * .ignoring() .antMatchers("/css/**","/image/**","/js/**"); }
	 */
}
