

package ru.studio.server.dao.impl;

import static ru.studio.server.constaint.Constaint.JDBC_URL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;
import ru.studio.server.dao.AdvancedDao;

/**
 * @author Angelina Kuzmina
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

	@Override
	public List<ClotheType> getAllClotherTypes()
	{
		List<ClotheType> clotheTypes = new ArrayList<>();

		String SQL = "SELECT id, name FROM clothetypes";

		try (Connection conn = connect();
				 Statement stmt = conn.createStatement())
		{
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next())
			{
				ClotheType clotheType = new ClotheType();
				clotheType.setId(rs.getInt("id"));
				clotheType.setName(rs.getString("name"));
				clotheTypes.add(clotheType);
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return clotheTypes;
	}

	@Override
	public ServiceType getServiceTypeByID(Integer id)
	{
		ServiceType serviceType = new ServiceType();

		String SQL = "SELECT * "
						+ "FROM servicetypes "
						+ "WHERE id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				serviceType.setId(rs.getInt("id"));
				serviceType.setName(rs.getString("name"));
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return serviceType;
	}

	@Override
	public RepairType getRepairTypeById(Integer id)
	{
		RepairType repairType = new RepairType();

		String SQL = "SELECT * "
						+ "FROM repairtypes "
						+ "WHERE id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				repairType.setId(rs.getInt("id"));
				repairType.setName(rs.getString("name"));
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return repairType;
	}

	@Override
	public ClotheType getClotheTypeById(Integer id)
	{
		ClotheType clotheType = new ClotheType();

		String SQL = "SELECT * "
						+ "FROM clothetypes "
						+ "WHERE id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				clotheType.setId(rs.getInt("id"));
				clotheType.setName(rs.getString("name"));
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return clotheType;
	}

	@Override
	public List<RepairType> getAllRepairTypes()
	{
		List<RepairType> repairTypes = new ArrayList<>();

		String SQL = "SELECT id, name FROM repairtypes";

		try (Connection conn = connect();
				 Statement stmt = conn.createStatement())
		{
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next())
			{
				RepairType repairType = new RepairType();
				repairType.setId(rs.getInt("id"));
				repairType.setName(rs.getString("name"));
				repairTypes.add(repairType);
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return repairTypes;
	}

	public Connection connect() throws SQLException
	{
		return DriverManager.getConnection(JDBC_URL);
	}
}
