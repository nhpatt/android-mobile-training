package com.liferay.myintranet.adapter;

import java.io.Serializable;

public class Role implements Serializable {

	private String name;
	private String description;

	public Role() {
		super();
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
