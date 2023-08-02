package br.com.unimedceara.core;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		setRegisterErrorPageFilter(true);
		return application.sources(BridgeApplication.class);
	}

}
