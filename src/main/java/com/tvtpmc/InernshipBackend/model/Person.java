package com.tvtpmc.InernshipBackend.model;

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
	private Set<Document> myDocuments = new HashSet<Document>();
	@OneToMany(mappedBy = "accepter", cascade = CascadeType.ALL)
	private Set<Document> acceptedDocuments = new HashSet<Document>();
	private String firstName;
	private String lastName;
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Admin admin;
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private User user;
}
