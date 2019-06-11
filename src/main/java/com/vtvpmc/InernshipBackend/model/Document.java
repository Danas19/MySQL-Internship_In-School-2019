package com.vtvpmc.InernshipBackend.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	@Column(columnDefinition="DATETIME")
	private Date sendedAtDate;
	@Column(columnDefinition="DATETIME")
	private Date acceptanceDate;
	@Column(columnDefinition="DATETIME")
	private Date declinationDate;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "accepter_id")
	private Person accepter;
	private String declinationReason;
	@OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
	@JsonIgnore
	List<PdfFile> pdfFiles;
	@Enumerated(EnumType.STRING)
	private State state;
	
	public Document() {
		pdfFiles = new ArrayList<>();
	}
	
	public Long getUniqueNumber() {
		return uniqueNumber;
	}
	
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
	
	public List<PdfFile> getPdfFiles() {
		return pdfFiles;
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
	
	public void setPdfFiles(List<PdfFile> pdfFiles) {
		this.pdfFiles = pdfFiles;
	}
	
	public void addPdfFile(PdfFile pdfFile) {
		pdfFiles.add(pdfFile);
	}

	public Date getSendedAtDate() {
		return sendedAtDate;
	}
	
	public State getState() {
		return state;
	}
	
	public void setAccepterStateAndDate(Person accepter, State state) {
		if (accepter == null || state == State.SUKURTAS || state == State.PATEIKTAS) {
			throw new IllegalArgumentException();
		}
		
		nullifyAcceptanceAndDeclinationDate();
		this.accepter = accepter;
		this.state = state;
		
		if (state == State.PRIIMTAS) {
			acceptanceDate = new Date(System.currentTimeMillis());
		} else {
			declinationDate = new Date(System.currentTimeMillis());
		}
	}
	
	public void nullifyAcceptanceAndDeclinationDate() {
		acceptanceDate = null;
		declinationDate = null;
	}

	public void setSendedAtDate(Date sendedAtDate) {
		this.sendedAtDate = sendedAtDate;
	}

	public void setStateToPATEIKTAS() {
		state = State.PATEIKTAS;
		sendedAtDate = new Date(System.currentTimeMillis());
	}
	
	public void setStateToSUKURTAS() {
		state = State.SUKURTAS;
	}
	
	public List<Long> getPdfFileIds() {
		return pdfFiles.stream()
		.map(f -> f.getId())
		.collect(Collectors.toList());
	}
	
	public String getDocumentTypeName() {
		return documentType.getTypeName();
	}
}
