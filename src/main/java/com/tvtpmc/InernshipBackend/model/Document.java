package com.tvtpmc.InernshipBackend.model;

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
}
