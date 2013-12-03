package com.easyTeach.common.dl;

import java.io.Serializable;

public class User implements Serializable {
    static final long serialVersionUID = 24325L;

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
    //private HashSet<Class> classes;
    //private HashMap<Question, boolean> questionState;
    //private HashSet<TestResults> testResults;

    public User(String firstName, String lastName, String emailAddress, String password) { 
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
    }

	public void setFirstName(String firstName) {
    	this.firstName = firstName;
	}

	public String getFirstName() {    
		return firstName;
	}

	public void setLastName(String lastName) {
    	this.lastName = lastName;
	}

	public String getLastName() {    
		return lastName;
	}

	public void setEmailAddress(String emailAddress) {
    	this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {    
		return emailAddress;
	}

	public void setPassword(String password) {
    	this.password = password;
	}

	public String getPassword() {    
		return password;
	}

}
