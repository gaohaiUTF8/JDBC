package com.jdbc1;

import java.sql.Date;


public class Customers {
	
	private int id;
	private String name;
	private String emailString;
	private Date birth;
	
	
	@Override
	public String toString() {
		return "Customers [id=" + id + ", name=" + name + ", emailString=" + emailString + ", birth=" + birth + "]";
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
	public String getEmailString() {
		return emailString;
	}
	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Customers() {
		super();

	}

	public Customers(int id, String name, String emailString, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.emailString = emailString;
		this.birth = birth;
	}

	
		
	

}
