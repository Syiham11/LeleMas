package com.dedek.lelemas.model;

public class Inventory {

	int id;
	String name;
	String description;

	// constructors
	public Inventory() {

	}

	public Inventory(String name) {
		this.name = name;
	}

	public Inventory(int id, String name, String description, int kg) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	// setter
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	// getter
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
