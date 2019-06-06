package com.vtvpmc.InernshipBackend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtvpmc.InernshipBackend.model.Document;
import com.vtvpmc.InernshipBackend.model.User;
import com.vtvpmc.InernshipBackend.model.UserGroup;
import com.vtvpmc.InernshipBackend.model.createCommands.CreateDocumentCommand;
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
	
	@PostMapping("/documents/{authorId}")
	public ResponseEntity<Document> addDocument(@RequestBody @Valid CreateDocumentCommand createDocumentCommand,
				@PathVariable Long authorId) {
		return new ResponseEntity<Document>(this.service.addDocument(createDocumentCommand, authorId), HttpStatus.CREATED);
	}
}
