package com.example.managercontroller;

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
public class ManagerControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerControllerApplication.class, args);
	}

}
