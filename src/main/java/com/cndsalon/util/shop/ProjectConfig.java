package com.cndsalon.util.shop;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration

public class ProjectConfig extends WebMvcConfigurationSupport {

	// CSS, webjars config
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").
		addResourceLocations("classpath:/META-INF/resources/webjars/");

		registry.addResourceHandler("/static/**").
		addResourceLocations("classpath:/static/");
//
//		registry.addResourceHandler("/ckeditor4/**").
//		addResourceLocations("classpath:/static/ckeditor4/");
//
		registry.addResourceHandler("/upload_image/**").
		addResourceLocations("file:///K:/data/upload_image/");

	}
}
