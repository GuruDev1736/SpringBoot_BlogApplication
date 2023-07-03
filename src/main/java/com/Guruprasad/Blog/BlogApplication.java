package com.Guruprasad.Blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog Application",
				description = "This is the documentation for the spring boot blog application CRUD REST Apis",
				contact = @Contact(
						name = "Guruprasad Bhagat",
						email = "guruprasad1736@gmail.com",
						url = "https://www.guruprasad.com"

				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.guruprasad.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "This is the blog application which is build using the spring boot technology with the help of java programming language",
				url = "https://github.com/guruprasad1736/SpringBoot_BlogApplication"
		)
)
public class BlogApplication {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
