package com.mxl.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mxl.demo.mapper")
public class FreightSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreightSystemApplication.class, args);
	}

}
