
package ru.studio.server.dao.impl;

import static ru.studio.server.constaint.Constaint.JDBC_URL;

import java.sql.*;

import ru.studio.api.model.role.Client;
import ru.studio.api.model.role.Tailor;
import ru.studio.api.model.role.User;
import ru.studio.server.dao.UserDao;


public class UserDaoImp implements UserDao
{
	@Override
	public User getUserById(Long id)
	{
		Tailor user = new Tailor();

		String SQL = "SELECT * "
						+ "FROM users "
						+ "WHERE id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery(SQL);

			while (rs.next())
			{
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("labelName"));
				user.setPassword((rs.getString("password")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUserByLogin(String login)
	{
		Client user = new Client();

		String SQL = "SELECT * "
						+ "FROM users "
						+ "WHERE login = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));
				user.setPassword((rs.getString("password")));
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return user;
	}

	@Override
	public void createUser(User user)
	{

	}

	public Connection connect() throws SQLException
	{
		return DriverManager.getConnection(JDBC_URL);
	}
}
