package com.example.memos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class MemosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemosApplication.class, args);
	}

}
