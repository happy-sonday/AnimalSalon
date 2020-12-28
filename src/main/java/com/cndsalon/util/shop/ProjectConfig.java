package com.cndsalon.util.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Project Configuration
 * 
 * @author <a href="simhyung777@naver.com">심현종</a></br>
 * @version 1.1 (2020-12-25)
 */
@Configuration
public class ProjectConfig extends WebMvcConfigurationSupport {

	// CSS, webjars config
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/**").
		addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/shop/upload_image/**").
		//addResourceLocations("file:///K:/data/upload_image/");
		addResourceLocations("file:/home/ec2-user/data/upload_image/");
		
		registry.addResourceHandler("/booking/upload_image/**")
//		.addResourceLocations("file:///E:/data/upload_image/");
		.addResourceLocations("file:/home/ec2-user/data/upload_image/");
		
	}
	
}
