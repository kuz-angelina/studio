

package ru.studio.server.dao;

import ru.studio.api.model.role.User;


public interface UserDao
{
	User getUserByLogin(String name);

	User getUserById(Long id);

	void createUser(User user);
}
