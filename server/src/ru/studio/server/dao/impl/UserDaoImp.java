
package ru.studio.server.dao.impl;

import static ru.studio.server.constaint.Constaint.JDBC_URL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.androidstudio.model.UserDto;
import com.example.androidstudio.model.role.Tailor;
import com.example.androidstudio.model.role.User;
import com.example.androidstudio.model.role.UserType;

import ru.studio.server.dao.UserDao;


public class UserDaoImp implements UserDao
{
	@Override
	public User getUserById(int id)
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
	public UserDto getUserByLogin(String login)
	{
		UserDto userDto = new UserDto();
		String SQL = "SELECT u.id uid, u.name uname, u.login, u.password, u.email, u.phoneNumber, ut.id utid, ut.name utname "
						+ "FROM users u inner join usertypes ut on u.usertype_id = ut.id"
						+ " WHERE login = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				userDto.setId(rs.getInt("uid"));
				userDto.setName(rs.getString("uname"));
				userDto.setLogin(rs.getString("login"));
				userDto.setEmail(rs.getString("email"));
				userDto.setPhoneNumber(rs.getString("phoneNumber"));
				userDto.setPassword((rs.getString("password")));
				userDto.setUserType(new UserType(rs.getInt("utid"), rs.getString("utname")));
			}
		}
		catch (
						SQLException ex)

		{
			System.out.println(ex.getMessage());
		}

		return userDto;
	}

	@Override
	public List<UserType> getAllUserType()
	{
		List<UserType> userTypeList = new ArrayList<>();

		String SQL = "SELECT id, name FROM usertypes";

		try (Connection conn = connect();
				 Statement stmt = conn.createStatement())
		{
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next())
			{
				UserType userType = new UserType();
				userType.setId(rs.getInt("id"));
				userType.setName(rs.getString("name"));
				userTypeList.add(userType);
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return userTypeList;
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
