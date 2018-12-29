
package ru.studio.server;

import ru.studio.api.model.role.User;
import ru.studio.server.dao.UserDaoImp;


public class AppStart
{
	public static void main(String[] args)
	{
		UserDaoImp userDaoImp = new UserDaoImp();
		User user = userDaoImp.getUserByName("tom");
	}
}
