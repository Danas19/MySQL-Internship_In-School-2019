package com.vtvpmc.InernshipBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.service.InternshipService;



@RestController
@CrossOrigin
@RequestMapping("/api/internship")
public class InternshipBackendController {
	@Autowired
	private InternshipService service;
	
	@PostMapping("/documents")
	public Document addDocument(Document document) {
		return this.service.addDocument(document);
	}
}

