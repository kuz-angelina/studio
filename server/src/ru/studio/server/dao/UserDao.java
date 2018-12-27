

package ru.studio.server.dao;

import ru.studio.api.model.role.User;


public interface UserDao
{
	User getUserByName(String name);

	User getUserById(Integer id);

	void createUser(User user);
}
