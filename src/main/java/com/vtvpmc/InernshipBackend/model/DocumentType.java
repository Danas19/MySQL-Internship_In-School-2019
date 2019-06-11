package com.vtvpmc.InernshipBackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class DocumentType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String typeName;
	@OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL)
	private Set<Document> documents;
	@ManyToMany
	@JoinTable(
			  name = "document_type_user_group_Create", 
			  joinColumns = @JoinColumn(name = "document_type_id"), 
			  inverseJoinColumns = @JoinColumn(name = "user_group_id"))
	private Set<UserGroup> groupsWhichCanCreate;
	@ManyToMany
	@JoinTable(
			  name = "document_type_user_group_Accept", 
			  joinColumns = @JoinColumn(name = "document_type_id"), 
			  inverseJoinColumns = @JoinColumn(name = "user_group_id"))
	private Set<UserGroup> groupsWhichCanAccept;
	
	public DocumentType() {
		documents = new HashSet<>();
	}
	
	public DocumentType(String typeName) {
		this();
		this.typeName = typeName;
	}
	
	public Long getId() {
		return id;
	}

	public String getTypeName() {
		return typeName;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	
	public void addDocument(Document document) {
		this.documents.add(document);
	}

	public Set<UserGroup> getGroupsWhichCanCreate() {
		return groupsWhichCanCreate;
	}

	public void setGroupsWhichCanCreate(Set<UserGroup> groupsWhichCanCreate) {
		this.groupsWhichCanCreate = groupsWhichCanCreate;
	}

	public Set<UserGroup> getGroupsWhichCanAccept() {
		return groupsWhichCanAccept;
	}

	public void setGroupsWhichCanAccept(Set<UserGroup> groupsWhichCanAccept) {
		this.groupsWhichCanAccept = groupsWhichCanAccept;
	}
	
	
}
