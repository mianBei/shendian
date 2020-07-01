package com.example.managerapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.example.managerDao")
@ServletComponentScan
public class ManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApiApplication.class, args);
	}

}
