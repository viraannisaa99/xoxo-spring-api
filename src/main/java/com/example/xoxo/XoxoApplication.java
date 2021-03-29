package com.example.xoxo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.xoxo.service.FileService;

import javax.annotation.Resource;



@SpringBootApplication
public class XoxoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XoxoApplication.class, args);
	}
	
}
