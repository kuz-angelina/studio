

package ru.studio.server.dao;

import java.util.List;

import com.example.androidstudio.model.UserDto;
import com.example.androidstudio.model.role.User;
import com.example.androidstudio.model.role.UserType;


public interface UserDao
{
	UserDto getUserByLogin(String name);

	User getUserById(int id);

	List<UserType> getAllUserType();

	void createUser(User user);
}
