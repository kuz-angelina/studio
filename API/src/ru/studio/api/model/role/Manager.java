package ru.studio.api.model.role;

/**
 * @author Angelina Kuzmina
 */
public class Manager extends User
{
	public Manager(Integer id, String login, String password, String name)
	{
		super(id, login, password, name);
	}

	public Manager()
	{
	}
}
