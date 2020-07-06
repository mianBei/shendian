package com.example.managerapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.example.managerDao")
@EnableSwagger2
@ComponentScan(basePackages = "com.example")
@ServletComponentScan
public class ManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApiApplication.class, args);
	}

}
