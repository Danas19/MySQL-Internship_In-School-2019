package com.vtvpmc.InernshipBackend.model;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uniqueNumber;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Person author;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "document_type_id")
	private DocumentType documentType;
	private String title;
	private String description;
	@CreationTimestamp
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date sendedAtDate;
	@Column(columnDefinition="TIMESTAMP DEFAULT '0000-00-00 00:00:00'")
	private Date acceptanceDate;
	@Column(columnDefinition="TIMESTAMP DEFAULT '0000-00-00 00:00:00'")
	private Date declinationDate;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "accepter_id")
	private Person accepter;
	private String declinationReason;
	@Lob
	private Blob file;
	
	public Person getAuthor() {
		return author;
	}
	
	public DocumentType getDocumentType() {
		return documentType;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getAcceptanceDate() {
		return acceptanceDate;
	}
	
	public Date getDeclinationDate() {
		return declinationDate;
	}
	
	public Person getAccepter() {
		return accepter;
	}
	
	public String getDeclinationReason() {
		return declinationReason;
	}
	
	public Blob getFile() {
		return file;
	}
	
	public void setAuthor(Person author) {
		this.author = author;
	}
	
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}
	
	public void setDeclinationDate(Date declinationDate) {
		this.declinationDate = declinationDate;
	}
	
	public void setAccepter(Person accepter) {
		this.accepter = accepter;
	}
	
	public void setDeclinationReason(String declinationReason) {
		this.declinationReason = declinationReason;
	}
	
	public void setFile(Blob file) {
		this.file = file;
	}

	public Date getSendedAtDate() {
		return sendedAtDate;
	}
	
	
}
