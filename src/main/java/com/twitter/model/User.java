package com.twitter.model;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "User")
public class User {
	@Id
	@Column(name="userId")
	private String id;

	@Column(name="userName")
	private String userName;
	
	protected User() {} //needed by JPA for authentication and construction
	
	public User(String userId, String name) {
		this.id = userId;
		this.userName = name;
	}

	public String getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public void setId(String userId) {
		this.id = userId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "{id:"+ getId() + ", " + "username:" + getUserName()+"}";
	}
	
	@Override
	public boolean equals(Object obj) {
		User other = (User)obj;
		if( this.id.equals(other.getId())
				&& this.getUserName().equals(other.getUserName()) )
			return true;
		return false;
	}
	
}
