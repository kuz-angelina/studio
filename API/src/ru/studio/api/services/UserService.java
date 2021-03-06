package ru.studio.api.services;

import java.util.List;

import ru.studio.api.model.UserDto;
import ru.studio.api.model.role.User;
import ru.studio.api.model.role.UserType;

/**
 * @author Angelina Kuzmina
 */
public interface UserService
{
	boolean createUser();

	User getUserById(Integer id);

	UserDto getUserByLogin(String login);

	List<UserType> getAllUserType();

	boolean saveUser(User user);
}
