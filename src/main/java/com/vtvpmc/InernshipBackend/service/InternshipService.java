package com.vtvpmc.InernshipBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.model.Person;
import com.vtvpmc.InernshipBackend.model.User;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserCommand;
import com.vtvpmc.InernshipBackend.repository.AdminRepository;
import com.vtvpmc.InernshipBackend.repository.DocumentRepository;
import com.vtvpmc.InernshipBackend.repository.DocumentTypeRepository;
import com.vtvpmc.InernshipBackend.repository.PersonRepository;
import com.vtvpmc.InernshipBackend.repository.UserGroupRepository;
import com.vtvpmc.InernshipBackend.repository.UserRepository;

@Service
public class InternshipService {
	private DocumentRepository documentRepository;
	private DocumentTypeRepository documentTypeRepository;
	private PersonRepository personRepository;
	private UserRepository userRepository;
	private UserGroupRepository userGroupRepository;
	private AdminRepository adminRepository;
	
	@Autowired
	public InternshipService(DocumentRepository documentRepository, DocumentTypeRepository documentTypeRepository,
			PersonRepository personRepository, UserRepository userRepository, UserGroupRepository userGroupRepository,
			AdminRepository adminRepository) {
		super();
		this.documentRepository = documentRepository;
		this.documentTypeRepository = documentTypeRepository;
		this.personRepository = personRepository;
		this.userRepository = userRepository;
		this.userGroupRepository = userGroupRepository;
		this.adminRepository = adminRepository;
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
	
	public User addUser(CreateUserCommand createUserCommand, Long userGroupId) {
		User newUser = new User();
		newUser.setUserGroup(userGroupRepository.findById(userGroupId).orElse(null));
		
		Person newPerson = new Person();
		newPerson.setFirstName(createUserCommand.getFirstName());
		newPerson.setLastName(createUserCommand.getLastName());
		newPerson.setUser(newUser);
		
		newUser.setPerson(newPerson);
		
		return newUser;
	}
}
