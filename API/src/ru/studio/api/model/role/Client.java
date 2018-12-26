package ru.studio.api.model.role;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Client extends User
{
	private String email;
	private String phoneNumber;

	public Client()
	{
	}

	public Client(Integer id, String login, String password, String name, String email, String phoneNumber)
	{
		super(id, login, password, name);
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

}
