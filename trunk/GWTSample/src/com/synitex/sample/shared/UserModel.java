package com.synitex.sample.shared;

import java.io.Serializable;

public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	public UserModel() {

	}

	public UserModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
