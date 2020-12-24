package com.cndsalon.util.shop;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration

public class ProjectConfig extends WebMvcConfigurationSupport {

	// CSS, webjars config
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/**").
		addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/shop/upload_image/**").
		addResourceLocations("file:///K:/data/upload_image/");
      
      //Server      addResourceLocations("file:///home/ec2-user/data/upload_image/");
	}
}
