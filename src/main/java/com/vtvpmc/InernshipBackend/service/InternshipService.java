package com.vtvpmc.InernshipBackend.service;

import java.util.List;
import java.util.Optional;

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
	
	public Admin getAdmin(Long adminId) {
		return this.adminRepository.findById(adminId).orElse(null);
	}
	
	public User getUser(Long userId) {
		return this.userRepository.findById(userId).orElse(null);
	}
	
	public UserGroup getUserGroup(Long userGroupId) {
		return this.userGroupRepository.findById(userGroupId).orElse(null);
	}
	
	public Document getDocument(Long documentId) {
		return this.documentRepository.findById(documentId).orElse(null);
	}
	
	public DocumentType getDocumentType(Long documentTypeId) {
		return this.documentTypeRepository.findById(documentTypeId).orElse(null);
	}
	
	public List<Document> getDocuments() {
		return this.documentRepository.findAll();
	}
	
	public List<DocumentType> getDocumentTypes() {
		return this.documentTypeRepository.findAll();
	}
	
	public List<Person> getPersons() {
		return this.personRepository.findAll();
	}
	
	public List<Admin> getAdmins() {
		return this.adminRepository.findAll();
	}
	
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}
	
	public List<UserGroup> getUserGroups() {
		return this.userGroupRepository.findAll();
	}
	
	public Optional<Admin> deletePersonByAdminId(Long adminId) {
		Optional<Admin> oldAdminOptional = adminRepository.findById(adminId);
		if (oldAdminOptional.isPresent()) {
			personRepository.deleteById(oldAdminOptional.get().getPerson().getId());
			return oldAdminOptional;
		}
		return oldAdminOptional;
	}
}
