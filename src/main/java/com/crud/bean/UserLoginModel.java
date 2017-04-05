package com.crud.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="register_user")
public class UserLoginModel {
	@Id
	private String id;
	private String emailid;
	private String password;
	
	public UserLoginModel(String sessionId) {
		// TODO Auto-generated constructor stub
	}
	public UserLoginModel() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		
}
