package ru.studio.api.model.role;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public abstract class User implements Serializable
{
	private Integer id;
	private String login;
	private String password;
	private String name;

	public User(Integer id, String login, String password, String name)
	{
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
	}

	public User()
	{
	}
}

