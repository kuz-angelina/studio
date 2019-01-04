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
import java.util.List;

import ru.studio.api.model.Order;
import ru.studio.api.model.role.User;
import ru.studio.api.model.service.Service;
import ru.studio.api.model.service.ServiceDate;
import ru.studio.api.model.service.ServiceRepair;
import ru.studio.api.model.service.ServiceSewing;
import ru.studio.server.constaint.Constaint;
import ru.studio.server.dao.OrderDao;

/**
 * @author viacheslav.iakovitskii@novardis.com
 * Created on 12/29/18
 */
public class OrderDaoImpl implements OrderDao
{
	public void saveServiceDate(ServiceDate serviceDate)
	{
		String SQL = "INSERT INTO servicedates VALUES (?,?,?,?)";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS))
		{
			pstmt.setDate(1, serviceDate.getMeasurements());
			pstmt.setDate(2, serviceDate.getModeling());
			pstmt.setDate(3, serviceDate.getPattern());
			pstmt.setDate(4, serviceDate.getStitching());

			pstmt.executeUpdate();

			serviceDate.setId(getModelId("ServiceDate", pstmt));
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}

	}


	public void saveService(Service service)
	{
		String serviceClassName = service.getClass().getSimpleName();
		String SQL = "INSERT INTO services (\"quantity\", \"service_type_id\", \"service_date_id\",\"repair_type_id\" ) VALUES (?,?,?,?)";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS))
		{
			pstmt.setInt(1, service.getQuantity());
			pstmt.setInt(2, service.getServiceType().getId());

			if (Constaint.SEWING_CLASS_NAME.equals(serviceClassName))
			{
				ServiceSewing serviceSewing = (ServiceSewing) service;
				pstmt.setInt(3, serviceSewing.getServiceDate().getId());
				pstmt.setInt(4, 0);
			}
			if (Constaint.REPAIR_CLASS_NAME.equals(serviceClassName))
			{
				ServiceRepair serviceRepair = (ServiceRepair) service;
				pstmt.setInt(3, 0);
				pstmt.setInt(4, serviceRepair.getRepairType().getId());
			}

			pstmt.executeUpdate();

			service.setId(getModelId(serviceClassName, pstmt));
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	private int getModelId(String className, PreparedStatement pstmt) throws SQLException
	{
		try (ResultSet generatedKeys = pstmt.getGeneratedKeys())
		{
			if (generatedKeys.next())
			{
				return generatedKeys.getInt(1);
			}
			else
			{
				throw new SQLException("Save " + className + " in DB failed, no ID obtained.");
			}
		}
	}

	@Override
	public void saveOrder(Order order)
	{
		String SQL = "INSERT INTO services VALUES (?,?,?,?,?,?,?,?,?)";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS))
		{

			pstmt.setBoolean(1, order.getTailorAssignment());
			pstmt.setInt(2, 0);
			//			pstmt.setInt(2, order.getManager().getId());
			pstmt.setInt(3, order.getClient().getId());
			//			pstmt.setInt(4, order.getTailor().getId());
			pstmt.setInt(4, 0);
			pstmt.setDouble(5, order.getCost());
			pstmt.setInt(6, order.getService().getId());
			pstmt.setInt(7, order.getClothesType().getId());
			pstmt.setBoolean(8, order.getComplete());
			pstmt.setBoolean(9, order.getGivenOut());

			pstmt.executeUpdate();

			order.setId(getModelId("Order", pstmt));
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public List<Order> getOrdersByUser(User user)
	{
		return null;
	}

	@Override
	public Order getOrderById(Integer id)
	{
		return null;
	}

	public Connection connect() throws SQLException
	{
		return DriverManager.getConnection(JDBC_URL);
	}
}
