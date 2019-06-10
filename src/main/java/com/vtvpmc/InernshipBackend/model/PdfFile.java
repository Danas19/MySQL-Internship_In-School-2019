package com.vtvpmc.InernshipBackend.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PdfFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private File file;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;

	public PdfFile(File file) {
		super();
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public File getFile() {
		return file;
	}

	public Document getDocument() {
		return document;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	
}

