package com.cafeteria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cafeteria.service.UploadService;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

	
	@Autowired
	private UploadService servicoUpload;
	
	@PostMapping("/{id}")	
	public void upload(@PathVariable(value = "id") Long id, @RequestBody MultipartFile arquivo) {
		servicoUpload.salvar("/jujuba", arquivo,id);
	}
}
