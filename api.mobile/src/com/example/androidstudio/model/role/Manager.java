package com.example.androidstudio.model.role;

/**
 * @author Angelina Kuzmina
 */
public class Manager extends User
{
	public Manager(Integer id, String login, String password, String name, UserType userType)
	{
		super(id, login, password, name, userType);
	}

	public Manager()
	{
	}
}
