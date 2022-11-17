package com.socialmedia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialmedia.services.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fService;
	
	@GetMapping("uploads/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getfile(@PathVariable String filename) {
		Resource file = fService.load(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + file.getFilename() + "\"").body(file); 
	}
}
