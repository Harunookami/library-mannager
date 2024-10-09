package com.library.mng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaRepositories("com.library.mng")
@ComponentScan(basePackages={"com.library.mng.controller", "com.library.mng.service"})
@SpringBootApplication
@EnableWebMvc
public class MngApplication {

	public static void main(String[] args) {
		SpringApplication.run(MngApplication.class, args);
	}

}
