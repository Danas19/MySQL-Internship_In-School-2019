package com.tvtpmc.InernshipBackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DocumentType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String typeName;
	@OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL)
	private Set<Document> documents;
	
	public DocumentType() {
		documents = new HashSet<>();
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
}
