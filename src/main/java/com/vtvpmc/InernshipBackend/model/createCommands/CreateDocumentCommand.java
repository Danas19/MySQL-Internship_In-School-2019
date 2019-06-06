package com.vtvpmc.InernshipBackend.model.createCommands;

import java.sql.Blob;
import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CreateDocumentCommand {
	@NotNull
	private Long documentTypeId;
	@NotNull
	private String title;
	@NotNull
	@Length(min = 1, max = 255)
	private String description;
	private Date sendedAtDate;
	private Date acceptanceDate;
	private Date declinationDate;
	private Long accepterId;
	private String declinationReason;
	private Blob file;
	
	public Long getDocumentTypeId() {
		return documentTypeId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getSendedAtDate() {
		return sendedAtDate;
	}
	
	public Date getAcceptanceDate() {
		return acceptanceDate;
	}
	
	public Date getDeclinationDate() {
		return declinationDate;
	}
	
	public Long getAccepterId() {
		return accepterId;
	}
	
	public String getDeclinationReason() {
		return declinationReason;
	}
	
	public Blob getFile() {
		return file;
	}
	
}
