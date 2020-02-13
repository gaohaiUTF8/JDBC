package com.jdbc1;


public class Students {

	private int id;
	private String name;
	
	
	public Students(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Students() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + "]";
	}


}
