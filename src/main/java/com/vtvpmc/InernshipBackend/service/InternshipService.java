package com.vtvpmc.InernshipBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.repository.DocumentRepository;

@Service
public class InternshipService {
	private DocumentRepository documentRepository;
	
	@Autowired
	public InternshipService(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}
	
	public Document addDocument(Document document) {
		return this.documentRepository.save(document);
	}
}
