package com.vtvpmc.InernshipBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentCommand;
import com.vtvpmc.InernshipBackend.repository.DocumentRepository;
import com.vtvpmc.InernshipBackend.repository.DocumentTypeRepository;
import com.vtvpmc.InernshipBackend.repository.PersonRepository;

@Service
public class InternshipService {
	private DocumentRepository documentRepository;
	private PersonRepository personRepository;
	private DocumentTypeRepository documentTypeRepository;
	
	@Autowired
	public InternshipService(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}
	
	public Document addDocument(CreateDocumentCommand createDocumentCommand, Long authorId) {
		Document newDocument = new Document();
		newDocument.setAuthor(personRepository.findById(authorId).orElse(null));
		
		newDocument.setDocumentType(documentTypeRepository.findById(createDocumentCommand.getDocumentTypeId()).orElse(null));
		newDocument.setTitle(createDocumentCommand.getTitle());
		newDocument.setDescription(createDocumentCommand.getDescription());
		
		if (createDocumentCommand.getFile() != null) {
			newDocument.setFile(createDocumentCommand.getFile());
		}
		
		return this.documentRepository.save(newDocument);
	}
}
