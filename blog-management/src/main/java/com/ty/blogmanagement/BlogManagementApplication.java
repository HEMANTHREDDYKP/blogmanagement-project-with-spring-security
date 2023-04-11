package com.ty.blogmanagement;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Blog Management", version = "1.0.0", description = "Blog Management app", termsOfService = "NA", contact = @Contact(name = "Hemanth KP", email = "hemanthkp831@gmail.com"), license = @License(name = "Licence", url = "https://www.testyantra.com/")))

/*
 * http://localhost:8080/swagger-ui/index.html ----> triger this url in browser
 */
public class BlogManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogManagementApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		System.err.println("flow --PASSWORD ENCODER--001");
		return new BCryptPasswordEncoder();
	}
}
