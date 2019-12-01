package com.example.androidstudio.services;

import java.util.List;

import com.example.androidstudio.model.UserDto;
import com.example.androidstudio.model.role.User;
import com.example.androidstudio.model.role.UserType;

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
