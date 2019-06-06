package com.vtvpmc.InernshipBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtvpmc.InernshipBackend.model.Admin;
import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.model.DocumentType;
import com.vtvpmc.InernshipBackend.model.Person;
import com.vtvpmc.InernshipBackend.model.User;
import com.vtvpmc.InernshipBackend.model.UserGroup;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateAdminCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentTypeCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserGroupCommand;
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


	public Document addDocument(CreateDocumentCommand createDocumentCommand, 
					Long documentTypeId, Long authorId) {
		Document newDocument = new Document();
		
		newDocument.setDocumentType(documentTypeRepository.findById(documentTypeId).orElse(null));
		documentTypeRepository.findById(documentTypeId).orElse(null).addDocument(newDocument);
		
		newDocument.setAuthor(personRepository.findById(authorId).orElse(null));
		personRepository.findById(authorId).orElse(null).addMyDocument(newDocument);
		
		
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
		
		Person newPerson = new Person(createUserCommand.getFirstName(),
		createUserCommand.getLastName());
		newPerson.setUser(newUser);
		
		newUser.setPerson(newPerson);
		
		personRepository.save(newPerson);
		return userRepository.save(newUser);
	}
	
	public UserGroup addUserGroup(CreateUserGroupCommand createUserGroupCommand) {
		UserGroup newUserGroup = new UserGroup();
		newUserGroup.setUserGroupName(createUserGroupCommand.getUserGroupName());
		
		return userGroupRepository.save(newUserGroup);
	}
	
	public Admin addAdmin(CreateAdminCommand createAdminCommand) {
		Admin newAdmin = new Admin();
		
		Person newPerson = new Person(createAdminCommand.getFirstName(),
				createAdminCommand.getLastName());
		newPerson.setAdmin(newAdmin);
		
		newAdmin.setPerson(newPerson);
		
		personRepository.save(newPerson);
		return adminRepository.save(newAdmin);
	}
	
	public DocumentType addDocumentType(CreateDocumentTypeCommand createDocumentTypeCommand) {
		DocumentType newDocumentType = new DocumentType();
		newDocumentType.setTypeName(createDocumentTypeCommand.getTypeName());
		
		return documentTypeRepository.save(newDocumentType);
	}
}
