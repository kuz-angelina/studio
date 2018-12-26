package ru.studio.api.services;

import ru.studio.api.model.role.User;

/**
 * @author Angelina Kuzmina
 */
public interface UserService
{
	boolean createUser();

	User getUserById(Integer id);

	User getUserByName(String name);

	boolean saveUser(User user);
}
