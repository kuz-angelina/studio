/*********************************************************************
 * The Initial Developer of the content of this file is NOVARDIS.
 * All portions of the code written by NOVARDIS are property of
 * NOVARDIS. All Rights Reserved.
 *
 * NOVARDIS
 * Detskaya st. 5A, 199106 
 * Saint Petersburg, Russian Federation 
 * Tel: +7 (812) 331 01 71
 * Fax: +7 (812) 331 01 70
 * www.novardis.com
 *
 * (c) 2018 by NOVARDIS
 *********************************************************************/

package ru.studio.server.dao.impl;

import static ru.studio.server.constaint.Constaint.JDBC_URL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ru.studio.api.model.service.ServiceType;
import ru.studio.server.dao.AdvancedDao;

/**
 * @author viacheslav.iakovitskii@novardis.com
 * Created on 12/29/18
 */
public class AdvancedDaoImpl implements AdvancedDao
{
	@Override
	public List<ServiceType> getAllServices()
	{
		List<ServiceType> serviceList = new ArrayList<>();

		String SQL = "SELECT id, name FROM servicetypes";

		try (Connection conn = connect();
				 Statement stmt = conn.createStatement())
		{
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next())
			{
				ServiceType serviceType = new ServiceType();
				serviceType.setId(rs.getInt("id"));
				serviceType.setName(rs.getString("name"));
				serviceList.add(serviceType);
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return serviceList;
	}

	public Connection connect() throws SQLException
	{
		return DriverManager.getConnection(JDBC_URL);
	}
}
