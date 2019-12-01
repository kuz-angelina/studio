

package ru.studio.server.service;

import java.util.List;

import com.caucho.hessian.server.HessianServlet;
import com.example.androidstudio.model.UserDto;
import com.example.androidstudio.model.role.User;
import com.example.androidstudio.model.role.UserType;
import com.example.androidstudio.services.UserService;

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
	public UserDto getUserByLogin(String login)
	{
		return userDao.getUserByLogin(login);
	}

	@Override
	public List<UserType> getAllUserType()
	{
		return userDao.getAllUserType();
	}

	@Override
	public boolean saveUser(User user)
	{
		return false;
	}
}
