package com.vtvpmc.InernshipBackend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtvpmc.InernshipBackend.model.Admin;
import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.model.DocumentType;
import com.vtvpmc.InernshipBackend.model.User;
import com.vtvpmc.InernshipBackend.model.UserGroup;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateAdminCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentTypeCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserCommand;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateUserGroupCommand;
import com.vtvpmc.InernshipBackend.service.InternshipService;



@RestController
@CrossOrigin
@RequestMapping("/api/internship")
public class InternshipBackendController {
	@Autowired
	private InternshipService service;
	
	@PostMapping("/userGroups")
	public ResponseEntity<UserGroup> addUserGroup(@RequestBody @Valid CreateUserGroupCommand createUserGroupCommand) {
		return new ResponseEntity<UserGroup>(this.service.addUserGroup(createUserGroupCommand), HttpStatus.CREATED);
	}
	
	@PostMapping("/userGroups/{userGroupId}/users")
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
	
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<Admin> getAdmin(@PathVariable Long adminId) {
		return ResponseEntity.ok().body(this.service.getAdmin(adminId));
	}
}

