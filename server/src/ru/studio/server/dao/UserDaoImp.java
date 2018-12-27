
package ru.studio.server.dao;

import java.sql.*;

import ru.studio.api.model.role.Tailor;
import ru.studio.api.model.role.User;


public class UserDaoImp implements UserDao
{

	public static final String JDBC_URL = "jdbc:postgresql://192.168.4.205:5432/studoiDB?user=postgres&password=root";

	@Override
	public User getUserById(Integer id)
	{
		Tailor user = new Tailor();

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {    //Получаем драйвер MySQL. DriverManager находит его по ClassPath, делает driver.accept(JDBC_URL) и если получает success то возращает нам экземпляр драйвера

			Statement stmt = conn.createStatement();    //Создаем стейтмент для запроса
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"Users\"");   //Создаем результсет для получения ответа

			while (rs.next()) { //перебираем наш результсет и выводим на консоль
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUserByName(String name)
	{
		Tailor user = new Tailor();

		String SQL = "SELECT * "
						+ "FROM users "
						+ "WHERE name = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, name);
		ResultSet	rs = pstmt.executeQuery();

			while (rs.next()) { //перебираем наш результсет и выводим на консоль
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword((rs.getString("password")));
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return user;
	}

	@Override
	public void createUser(User user)
	{

	}

	public Connection connect() throws SQLException {
		return DriverManager.getConnection(JDBC_URL);
	}
}
