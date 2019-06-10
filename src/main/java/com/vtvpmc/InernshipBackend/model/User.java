package com.vtvpmc.InernshipBackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@ManyToMany
	@JoinTable(
	  name = "us3r_us3r_gr0up", 
	  joinColumns = @JoinColumn(name = "us3r_id"), 
	  inverseJoinColumns = @JoinColumn(name = "gr0up_id"))
	 Set<UserGroup> gr0ups;
	
	public User() {
		gr0ups = new HashSet<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public Set<UserGroup> getGroups() {
		return gr0ups;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void setGroups(Set<UserGroup> groups) {
		this.gr0ups = groups;
	}
	
	public void addGroup(UserGroup group) {
		this.gr0ups.add(group);
	}
	
	
}
