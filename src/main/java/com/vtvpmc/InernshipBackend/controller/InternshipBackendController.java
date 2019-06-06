package com.vtvpmc.InernshipBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Document> addDocument(Document document) {
		return new ResponseEntity<Document>(this.service.addDocument(document), HttpStatus.CREATED);
	}
}

