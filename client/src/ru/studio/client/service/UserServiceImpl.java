package ru.studio.client.service;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;

import ru.studio.api.services.UserService;
import ru.studio.api.model.role.User;

/**
 * @author Angelina Kuzmina
 */
public class UserServiceImpl implements UserService
{
	String url = "http://localhost:8080/unnamed/user";
	HessianProxyFactory factory = new HessianProxyFactory();
	UserService userService;

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
		try
		{
			userService = (UserService) factory.create(UserService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return userService.getUserByName(name);
	}

	@Override
	public boolean saveUser(User user)
	{
		return false;
	}
}
