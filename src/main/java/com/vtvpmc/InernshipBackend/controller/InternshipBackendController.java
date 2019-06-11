package com.vtvpmc.InernshipBackend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtvpmc.InernshipBackend.model.Admin;
import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.model.DocumentType;
import com.vtvpmc.InernshipBackend.model.Person;
import com.vtvpmc.InernshipBackend.model.User;
import com.vtvpmc.InernshipBackend.model.UserGroup;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateAdminCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentTypeCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreatePersonCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserGroupCommand;
import com.vtvpmc.InernshipBackend.service.InternshipService;



@RestController
@CrossOrigin
@RequestMapping("/api/internship")
public class InternshipBackendController {
	@Autowired
	private InternshipService service;
	
	@GetMapping("/documents")
	public ResponseEntity<List<Document>> getDocuments() {
		return ResponseEntity.ok().body(this.service.getDocuments());
	}
	
	@GetMapping("/documentTypes")
	public ResponseEntity<List<DocumentType>> getDocumentTypes() {
		return ResponseEntity.ok().body(this.service.getDocumentTypes());
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(this.service.getUsers());
	}
	
	@GetMapping("/userGroups")
	public ResponseEntity<List<UserGroup>> getUserGroups() {
		return ResponseEntity.ok().body(this.service.getUserGroups());
	}
	
	@GetMapping("/admins")
	public ResponseEntity<List<Admin>> getAdmins() {
		return ResponseEntity.ok().body(this.service.getAdmins());
	}
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getPersons() {
		return ResponseEntity.ok().body(this.service.getPersons());
	}
	
	
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<Admin> getAdmin(@PathVariable Long adminId) {
		return ResponseEntity.ok().body(this.service.getAdmin(adminId));
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		return ResponseEntity.ok().body(this.service.getUser(userId));
	}
	
	@GetMapping("/documents/{documentId}")
	public ResponseEntity<Document> getDocument(@PathVariable Long documentId) {
		return ResponseEntity.ok().body(this.service.getDocument(documentId));
	}
	
	@GetMapping("/documentTypes/{documentTypeId}")
	public ResponseEntity<DocumentType> getdocumentType(@PathVariable Long documentTypeId) {
		return ResponseEntity.ok().body(this.service.getDocumentType(documentTypeId));
	}
	
	@GetMapping("/userGroups/{userGroupId}")
	public ResponseEntity<UserGroup> getUserGroup(@PathVariable Long userGroupId) {
		return ResponseEntity.ok().body(this.service.getUserGroup(userGroupId));
	}
	
	@PostMapping("/testData/create")
	public ResponseEntity createTestData() {
		service.createTestData();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/userGroups")
	public ResponseEntity<UserGroup> addUserGroup(@RequestBody @Valid CreateUserGroupCommand createUserGroupCommand) {
		return new ResponseEntity<UserGroup>(this.service.addUserGroup(createUserGroupCommand), HttpStatus.CREATED);
	}
	
	@PostMapping("/users/{userGroupId}")
	public ResponseEntity<User> addUser(@RequestBody @Valid CreateUserCommand createUserCommand,
				@PathVariable Long userGroupId) {
		return new ResponseEntity<User>(this.service.addUser(createUserCommand, userGroupId), HttpStatus.CREATED);
	}
	
	@PostMapping("/admins")
	public ResponseEntity<Admin> addAdmin(@RequestBody @Valid CreateAdminCommand createAdminCommand) {
		return new ResponseEntity<Admin>(this.service.addAdmin(createAdminCommand), HttpStatus.CREATED);
	}
	
	@PostMapping("/documentTypes")
	public ResponseEntity<DocumentType> addDocumentType(@RequestBody @Valid CreateDocumentTypeCommand createDocumentTypeCommand) {
		return new ResponseEntity<DocumentType>(this.service.addDocumentType(createDocumentTypeCommand), HttpStatus.CREATED);
	}
	
	@PostMapping("/documents/{documentTypeId}/{authorId}")
	public ResponseEntity<Document> addDocument(@RequestBody @Valid CreateDocumentCommand createDocumentCommand,
				@PathVariable Long documentTypeId, @PathVariable Long authorId) {
		return new ResponseEntity<Document>(this.service.addDocument(createDocumentCommand, documentTypeId, authorId),
					HttpStatus.CREATED);
	}
	
	@PutMapping("/userGroups/{userGroupId}")
	public ResponseEntity<UserGroup> setUserGroup(@RequestBody @Valid CreateUserGroupCommand createUserGroupCommand,
			@PathVariable Long userGroupId) {
		return new ResponseEntity<UserGroup>(this.service
				.setUserGroup(createUserGroupCommand, userGroupId), HttpStatus.CREATED);
	}
	
	@PutMapping("/persons/{personId}")
	public ResponseEntity<Person> setPerson(@RequestBody @Valid CreatePersonCommand createPersonCommand,
			@PathVariable Long personId) {
		return new ResponseEntity<Person>(this.service
				.setPerson(createPersonCommand, personId), HttpStatus.CREATED);
	}
	
	@PutMapping("/documentTypes/{documentTypeId}")
	public ResponseEntity<DocumentType> setDocumentType(@RequestBody @Valid CreateDocumentTypeCommand createDocumentTypeCommand,
			@PathVariable Long documentTypeId) {
		return new ResponseEntity<DocumentType>(this.service
				.setDocumentType(createDocumentTypeCommand, documentTypeId), HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping("/admins/{adminId}/persons")
	public ResponseEntity<Admin> deletePersonByAdminId(@PathVariable Long adminId) {
		Optional<Admin> adminToBeDeletedOptional = service.deletePersonByAdminId(adminId);
		if (adminToBeDeletedOptional.isPresent()) {
			return ResponseEntity.ok().body(adminToBeDeletedOptional.get());
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@DeleteMapping("/users/{userId}/persons")
	public ResponseEntity<User> deletePersonByUserId(@PathVariable Long userId) {
		Optional<User> userToBeDeletedOptional = service.deletePersonByUserId(userId);
		if (userToBeDeletedOptional.isPresent()) {
			return ResponseEntity.ok().body(userToBeDeletedOptional.get());
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@DeleteMapping("/admins/{adminId}")
	public ResponseEntity<Admin> deleteAdminRights(@PathVariable Long adminId) {
		return ResponseEntity.ok().body(service.deleteAdminRights(adminId));
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<User> deleteUserRights(@PathVariable Long userId) {
		return ResponseEntity.ok().body(service.deleteUserRights(userId));
	}
	
	@DeleteMapping("/documents/{documentId}")
	public ResponseEntity<Document> deleteDocument(@PathVariable Long documentId) {
		return ResponseEntity.ok().body(service.deleteDocument(documentId));
	}
	
	@DeleteMapping("/documentTypes/{documentTypeId}")
	public ResponseEntity<DocumentType> deleteDocumentType(@PathVariable Long documentTypeId) {
		return ResponseEntity.ok().body(service.deleteDocumentType(documentTypeId));
	}
	
	@DeleteMapping("/persons/{personId}")
	public ResponseEntity<Person> deletePerson(@PathVariable Long personId) {
		return ResponseEntity.ok().body(service.deletePerson(personId));
	}
	
	@DeleteMapping("/userGroups/{userGroupId}")
	public ResponseEntity<UserGroup> deleteUserGroup(@PathVariable Long userGroupId) {
		return ResponseEntity.ok().body(service.deleteUserGroup(userGroupId));
	}
	
}

