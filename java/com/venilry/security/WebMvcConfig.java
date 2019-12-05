package com.venilry.security;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private String staticPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//静态资源文件夹的全路径
		registry.addResourceHandler("/image/**")
				.addResourceLocations(staticPath + File.separator + "image" + File.separator);
		registry.addResourceHandler("/css/**")
		.addResourceLocations(staticPath + File.separator + "css" + File.separator);
		registry.addResourceHandler("/js/**")
		.addResourceLocations(staticPath + File.separator + "js" + File.separator);
		registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
	}



}
