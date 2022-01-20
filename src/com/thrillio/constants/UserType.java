package com.thrillio.constants;

//Constant export class
public enum UserType {
	 USER("user"),
	 EDITOR("editor"),
	 CHIEF_EDITOR("cheifeditor");
	
	private UserType(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}
	
}
