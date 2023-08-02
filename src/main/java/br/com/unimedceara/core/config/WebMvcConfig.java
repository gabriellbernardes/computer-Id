package br.com.unimedceara.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/images/**").addResourceLocations(System.getProperty("user.home") + File.separator + "images");
		registry.addResourceHandler("/temp/**").addResourceLocations("classpath:/static/temp/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}