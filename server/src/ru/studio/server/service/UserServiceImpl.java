

package ru.studio.server.service;

import com.caucho.hessian.server.HessianServlet;

import ru.studio.api.model.role.User;
import ru.studio.api.services.UserService;
import ru.studio.server.dao.UserDao;
import ru.studio.server.dao.impl.UserDaoImp;


/**
 * @author Angelina Kuzmina
 */

public class UserServiceImpl extends HessianServlet implements UserService
{

	UserDao userDao = new UserDaoImp();

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
	public User getUserByLogin(String login)
	{
//		Map<String, User> users = new HashMap<>();
//
//		Client client = new Client(1, "Vasya", "123", "Vasya", "emailV", "777");
//		Manager manager = new Manager(1, "Petya", "123", "Petya");
//		Tailor tailor = new Tailor(1, "Kolya", "123", "Kolya", "999", "addrK", 2, new Date());
//
//		users.put(client.getName(), client);
//		users.put(manager.getName(), manager);
//		users.put(tailor.getName(), tailor);

		User user = userDao.getUserByLogin(login);

		return user;
	}

	@Override
	public boolean saveUser(User user)
	{
		return false;
	}
}
