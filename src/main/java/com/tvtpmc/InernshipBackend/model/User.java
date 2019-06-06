package com.tvtpmc.InernshipBackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_group_id")
	private UserGroup userGroup;
	
	public Person getPerson() {
		return person;
	}
	
	public UserGroup getUserGroup() {
		return userGroup;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	
	
}
