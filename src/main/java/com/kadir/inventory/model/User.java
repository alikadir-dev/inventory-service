package com.kadir.inventory.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_info")
public class User extends BaseEntity
{
	private String fullname;
	private int password;
	private String userType;
	private String email;
	private boolean enabled;
	private String role;

	public User()
	{
	}

	public User(String fullname, int password, String userType, String email, boolean enabled, String role)
	{
		super();
		this.fullname = fullname;
		this.password = password;
		this.userType = userType;
		this.email = email;
		this.enabled = enabled;
		this.role = role;
	}

	public String getFullname()
	{
		return fullname;
	}

	public void setFullname(String fullname)
	{
		this.fullname = fullname;
	}

	public int getPassword()
	{
		return password;
	}

	public void setPassword(int password)
	{
		this.password = password;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public String getRoles()
	{
		return role;
	}

	public void setRoles(String role)
	{
		this.role = role;
	}

}
