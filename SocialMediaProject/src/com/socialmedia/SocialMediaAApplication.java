package com.socialmedia;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.socialmedia.services.FileService;

@SpringBootApplication
public class SocialMediaAApplication implements CommandLineRunner {

	@Resource
	FileService fileService;
	
	public static void main(String[] args) {
		SpringApplication.run(SocialMediaAApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Override
	public void run(String... arg) throws Exception {
		this.fileService.init();
	}

}
