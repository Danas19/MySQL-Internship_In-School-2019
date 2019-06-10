package com.vtvpmc.InernshipBackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userGroupName;
	@ManyToMany(mappedBy = "gr0ups")
	private Set<User> us3rs;
	@ManyToMany(mappedBy = "groupsWhichCanCreate")
	private Set<DocumentType> documentTypesWeCanCreate;
	@ManyToMany(mappedBy = "groupsWhichCanAccept")
	private Set<DocumentType> documentTypesWeCanAccept;
	
	public UserGroup() {
		us3rs = new HashSet<>();
	}
	
	public Long getId() {
		return id;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	@JsonIgnore
	public Set<User> getUsers() {
		return us3rs;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public void setUsers(Set<User> users) {
		this.us3rs = users;
	}
	
	public void addUser(User user) {
		us3rs.add(user);
	}

	public Set<DocumentType> getDocumentTypesWeCanCreate() {
		return documentTypesWeCanCreate;
	}

	public void setDocumentTypesWeCanCreate(Set<DocumentType> documentTypesWeCanCreate) {
		this.documentTypesWeCanCreate = documentTypesWeCanCreate;
	}

	public Set<DocumentType> getDocumentTypesWeCanAccept() {
		return documentTypesWeCanAccept;
	}

	public void setDocumentTypesWeCanAccept(Set<DocumentType> documentTypesWeCanAccept) {
		this.documentTypesWeCanAccept = documentTypesWeCanAccept;
	}
	
}
