package br.com.unimedceara.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurer {
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("br.com.unimedceara.jobs.rest"))
	                .paths(PathSelectors.any())
	                .build()
	                .useDefaultResponseMessages(false)
	                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
	                .title("Webhook Chatbot - Whatsapp")
	                .description("API Rest de Integração do WhatsApp Nacional Unimed")
	                .version("0.0.1")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	                .contact(new Contact("Suporte Dev", "https://www.unimedceara.com.br/", "contato@unimedceara.com.br"))
	                .build();
    }
}