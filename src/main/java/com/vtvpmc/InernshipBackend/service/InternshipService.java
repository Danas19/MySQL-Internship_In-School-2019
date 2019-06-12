package com.vtvpmc.InernshipBackend.service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtvpmc.InernshipBackend.TestData;
import com.vtvpmc.InernshipBackend.model.Admin;
import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.model.DocumentType;
import com.vtvpmc.InernshipBackend.model.PdfFile;
import com.vtvpmc.InernshipBackend.model.Person;
import com.vtvpmc.InernshipBackend.model.User;
import com.vtvpmc.InernshipBackend.model.UserGroup;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateAdminCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentTypeCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreatePersonCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserGroupCommand;
import com.vtvpmc.InernshipBackend.repository.AdminRepository;
import com.vtvpmc.InernshipBackend.repository.DocumentRepository;
import com.vtvpmc.InernshipBackend.repository.DocumentTypeRepository;
import com.vtvpmc.InernshipBackend.repository.PdfFileRepository;
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
	private PdfFileRepository pdfFileRepository;
	
	@Autowired
	TestData testData;
	
	@Autowired
	public InternshipService(DocumentRepository documentRepository, DocumentTypeRepository documentTypeRepository,
			PersonRepository personRepository, UserRepository userRepository, UserGroupRepository userGroupRepository,
			AdminRepository adminRepository, PdfFileRepository pdfFileRepository) {
		super();
		this.documentRepository = documentRepository;
		this.documentTypeRepository = documentTypeRepository;
		this.personRepository = personRepository;
		this.userRepository = userRepository;
		this.userGroupRepository = userGroupRepository;
		this.adminRepository = adminRepository;
		this.pdfFileRepository = pdfFileRepository;
	}

	public void createTestData() {
		testData.createTestData();
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
	
	public List<User> getUsersNotBelongingToGroup(Long userGroupId) {
		return userRepository.findAll().stream()
		.filter(u -> !isUserInGroup(u, userGroupId))
		.collect(Collectors.toList());
	}
	
	private boolean isUserInGroup(User user, Long userGroupId) {
		for (UserGroup userGroup : user.getGroups()) {
			if (userGroup.getId().equals(userGroupId)) {
				return true;
			}
		}
		return false;
	}
	
	public List<UserGroup> getUserGroups() {
		return this.userGroupRepository.findAll();
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
	
	
	public Document addDocument(CreateDocumentCommand createDocumentCommand, 
					Long documentTypeId, Long authorId) {
		Document newDocument = new Document();
		
		newDocument.setDocumentType(documentTypeRepository.findById(documentTypeId).orElse(null));
		documentTypeRepository.findById(documentTypeId).orElse(null).addDocument(newDocument);
		
		newDocument.setAuthor(personRepository.findById(authorId).orElse(null));
		personRepository.findById(authorId).orElse(null).addMyDocument(newDocument);
		
		
		newDocument.setTitle(createDocumentCommand.getTitle());
		newDocument.setDescription(createDocumentCommand.getDescription());
		
		if (createDocumentCommand.getFiles() != null 
						&& createDocumentCommand.getFiles().size() > 0) {
			for (File file : createDocumentCommand.getFiles()) {
				PdfFile pdfFile = new PdfFile(file);
				newDocument.addPdfFile(pdfFile);
				pdfFile.setDocument(newDocument);
				pdfFileRepository.save(pdfFile);
			}
			//createDocumentCommand.getFiles().forEach(System.out::println);
		}
		
		return this.documentRepository.save(newDocument);
	}
	
	public User addUser(CreateUserCommand createUserCommand, Long userGroupId) {
		User newUser = new User();
		UserGroup oldUserGroup = userGroupRepository.findById(userGroupId).orElse(null);
		newUser.addGroup(oldUserGroup);
		oldUserGroup.addUser(newUser);
		
		Person newPerson = new Person(createUserCommand.getFirstName(),
		createUserCommand.getLastName());
		newPerson.setUser(newUser);
		
		newUser.setPerson(newPerson);
		
		personRepository.save(newPerson);
		return userRepository.save(newUser);
	}
	
	public User addUserToGroup(Long userId, Long userGroupId) {
		User oldUser = userRepository.findById(userId).orElse(null);
		UserGroup oldUserGroup = userGroupRepository.findById(userGroupId).orElse(null);
		oldUser.addGroup(oldUserGroup);
		oldUserGroup.addUser(oldUser);
		
		userGroupRepository.save(oldUserGroup);
		return userRepository.save(oldUser);
	}
	
	public List<User> addUsersToGroup(List<User> users, Long userGroupId) {
		for (User temporary : users) {
			addUserToGroup(temporary.getId(), userGroupId);
		}
		return users;
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
	
	public UserGroup setUserGroup(CreateUserGroupCommand createUserGroupCommand, Long userGroupId) {
		UserGroup oldUserGroup = userGroupRepository.findById(userGroupId).orElse(null);
		oldUserGroup.setUserGroupName(createUserGroupCommand.getUserGroupName());
		return oldUserGroup;
	}
	
	public Person setPerson(CreatePersonCommand createPersonCommand, Long personId) {
		Person oldPerson = personRepository.findById(personId).orElse(null);
		oldPerson.setFirstName(createPersonCommand.getFirstName());
		oldPerson.setLastName(createPersonCommand.getLastName());
		return oldPerson;
	}
	
	public DocumentType setDocumentType(CreateDocumentTypeCommand createDocumentTypeCommand, Long documentTypeId) {
		DocumentType oldDocumentType = documentTypeRepository.findById(documentTypeId).orElse(null);
		oldDocumentType.setTypeName(createDocumentTypeCommand.getTypeName());
		return oldDocumentType;
	}
	
	
	
	
	
	public Optional<Admin> deletePersonByAdminId(Long adminId) {
		Optional<Admin> oldAdminOptional = adminRepository.findById(adminId);
		if (oldAdminOptional.isPresent()) {
			personRepository.deleteById(oldAdminOptional.get().getPerson().getId());
			return oldAdminOptional;
		}
		return oldAdminOptional;
	}
	
	public Optional<User> deletePersonByUserId(Long userId) {
		Optional<User> oldUserOptional = userRepository.findById(userId);
		if (oldUserOptional.isPresent()) {
			userRepository.deleteById(oldUserOptional.get().getPerson().getId());
			return oldUserOptional;
		}
		return oldUserOptional;
	}
	
	public Admin deleteAdminRights(Long adminId) {
		Admin oldAdmin = adminRepository.findById(adminId).orElse(null);
		adminRepository.deleteById(adminId);
		return oldAdmin;
	}
	
	public User deleteUserRights(Long userId) {
		User oldUser = userRepository.findById(userId).orElse(null);
		userRepository.deleteById(userId);
		return oldUser;
	}
	
	public Document deleteDocument(Long documentId) {
		Document oldDocument = documentRepository.findById(documentId).orElse(null);
		documentRepository.deleteById(documentId);
		return oldDocument;
	}
	
	public DocumentType deleteDocumentType(Long documentTypeId) {
		DocumentType oldDocumentType = documentTypeRepository.findById(documentTypeId).orElse(null);
		documentTypeRepository.deleteById(documentTypeId);
		return oldDocumentType;
	}
	
	public Person deletePerson(Long personId) {
		Person oldPerson = personRepository.findById(personId).orElse(null);
		personRepository.deleteById(personId);
		return oldPerson;
	}
	
	public UserGroup deleteUserGroup(Long userGroupId) {
		UserGroup oldUserGroup = userGroupRepository.findById(userGroupId).orElse(null);
		userGroupRepository.deleteById(userGroupId);
		return oldUserGroup;
	}
	
}
