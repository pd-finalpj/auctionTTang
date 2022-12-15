package com.encore.auction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.encore.auction.interceptor.BearerAuthInterceptor;

@Configuration
@EnableScheduling
public class WebConfig implements WebMvcConfigurer {

	private final BearerAuthInterceptor bearerAuthInterceptor;

	public WebConfig(BearerAuthInterceptor bearerAuthInterceptor) {
		this.bearerAuthInterceptor = bearerAuthInterceptor;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000")
			.allowedMethods("*");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(bearerAuthInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/auction/get/**", "/notice/get/**", "/user/login", "/user/sign-up")
			.excludePathPatterns("/swagger-ui.html", "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs",
				"/v2/api-docs");
	}

	// @Bean
	// public CommonsMultipartResolver multipartResolver() {
	// 	CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	// 	commonsMultipartResolver.setDefaultEncoding("UTF-8");
	// 	commonsMultipartResolver.setMaxUploadSizePerFile(500 * 1024 * 1024);
	// 	return commonsMultipartResolver;
	// }
}
