package com.vtvpmc.InernshipBackend.model.createCommands;

import java.sql.Blob;
import java.sql.Date;

public class CreateDocumentCommand {
	private Long authorId;
	private Long documentTypeId;
	private String title;
	private String description;
	private Date sendedAtDate;
	private Date acceptanceDate;
	private Date declinationDate;
	private Long accepterId;
	private String declinationReason;
	private Blob file;
	
	public Long getAuthorId() {
		return authorId;
	}
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
