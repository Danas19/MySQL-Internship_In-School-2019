package com.vtvpmc.InernshipBackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userGroupName;
	@OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL)
	private Set<User> users;
	
	public UserGroup() {
		users = new HashSet<>();
	}
	
	public Long getId() {
		return id;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
}
