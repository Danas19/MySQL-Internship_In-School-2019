package com.vtvpmc.InernshipBackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private Set<Document> myDocuments;
	@OneToMany(mappedBy = "accepter", cascade = CascadeType.ALL)
	private Set<Document> myAcceptedDocuments;
	private String firstName;
	private String lastName;
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Admin admin;
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private User user;
	
	public Person() {
		myDocuments = new HashSet<Document>();
		myAcceptedDocuments = new HashSet<Document>();
	}

	public Set<Document> getMyDocuments() {
		return myDocuments;
	}

	public Set<Document> getMyAcceptedDocuments() {
		return myAcceptedDocuments;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Admin getAdmin() {
		return admin;
	}

	public User getUser() {
		return user;
	}

	public void setMyDocuments(Set<Document> myDocuments) {
		this.myDocuments = myDocuments;
	}

	public void setMyAcceptedDocuments(Set<Document> myAcceptedDocuments) {
		this.myAcceptedDocuments = myAcceptedDocuments;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void addMyDocument(Document document) {
		myDocuments.add(document);
	}
	
	public void addMyAcceptedDocuments(Document document) {
		this.myAcceptedDocuments.add(document);
	}
}
