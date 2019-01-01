
package ru.studio.server;

import ru.studio.api.model.role.User;
import ru.studio.server.dao.impl.UserDaoImp;

public class AppStart
{
	public static void main(String[] args)
	{
		UserDaoImp userDaoImp = new UserDaoImp();
		User user = userDaoImp.getUserByLogin("tom");
//		AdvancedDao advancedDao = new AdvancedDaoImpl();
//		List<ServiceType> serviceTypes = advancedDao.getAllServices();
	}
}
