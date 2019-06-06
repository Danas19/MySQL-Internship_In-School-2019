package com.vtvpmc.InernshipBackend.model.createCommands;

public class CreateUserCommand extends CreatePersonCommand {
	private Long userGroupId;
	
	public Long getUserGroupId() {
		return userGroupId;
	}
	
}
