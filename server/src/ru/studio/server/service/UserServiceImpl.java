

package ru.studio.server.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.caucho.hessian.server.HessianServlet;

import ru.studio.api.model.role.Client;
import ru.studio.api.model.role.Manager;
import ru.studio.api.model.role.Tailor;
import ru.studio.api.model.role.User;
import ru.studio.api.services.UserService;



/**
 * @author Angelina Kuzmina
 */

public class UserServiceImpl extends HessianServlet implements UserService
{
	@Override
	public boolean createUser()
	{
		return false;
	}

	@Override
	public User getUserById(Integer id)
	{
		return null;
	}

	@Override
	public User getUserByName(String name)
	{
		Map<String, User> users = new HashMap<>();

		Client client = new Client(1, "Vasya", "123", "Vasya", "emailV", "777");
		Manager manager = new Manager(1, "Petya", "123", "Petya");
		Tailor tailor = new Tailor(1, "Kolya", "123", "Kolya", "999", "addrK", 2, new Date());

		users.put(client.getName(), client);
		users.put(manager.getName(), manager);
		users.put(tailor.getName(), tailor);

		return users.getOrDefault(name, null);
	}

	@Override
	public boolean saveUser(User user)
	{
		return false;
	}
}
