package com.socialmedia.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.validation.Path;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.socialmedia.validators.MultipartConverter;

@Service
public class FileService implements WebMvcConfigurer {
	private final java.nio.file.Path root = Paths.get("uploads");

	
	public void init() throws IOException {
		if (! Files.exists(root)) {
			try {
				Files.createDirectory(root);
			} catch (IOException e){
				throw new RuntimeException("Could not initialize folder for upload");
			}
		}
		
	}
	
	public String save(MultipartFile file) {
		try {
			java.nio.file.Path savePath = this.root.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), savePath);
			return savePath.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Resource load(String filename) {
		try {
			java.nio.file.Path file = root.resolve(filename);
			Resource resource = new  UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file");
			}
		} catch(MalformedURLException e) {
			throw new RuntimeException("Error:" + e.getMessage());
		}
	}
	
    @Override
    public void addFormatters(FormatterRegistry registry) {
         registry.addConverter(new MultipartConverter());
     }

    
}
