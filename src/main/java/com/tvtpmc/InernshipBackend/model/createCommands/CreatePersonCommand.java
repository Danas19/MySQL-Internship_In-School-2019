package com.tvtpmc.InernshipBackend.model.createCommands;

import java.util.HashSet;
import java.util.Set;

import com.tvtpmc.InernshipBackend.model.Document;
import com.tvtpmc.InernshipBackend.model.User;
import com.tvtpmc.InernshipBackend.model.UserGroup;

public class CreatePersonCommand {
	private Set<Document> myDocuments = new HashSet<Document>();
	private Set<Document> acceptedDocuments = new HashSet<Document>();
	private String firstName;
	private String lastName;
	private Long adminId;
	private User userId;
	private UserGroup userGroup;
}
