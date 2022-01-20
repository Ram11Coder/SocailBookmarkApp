package com.thrillio.constants;

public enum Gender {

	MALE(0),
	FEMALE(1),
	TRANSGENDER(2);

	private Gender(int gender) {
		this.gender = gender;
	}

	private int gender;

	public int getGender() {
		return gender;
	}
}
