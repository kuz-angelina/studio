

package ru.studio.server.dao.impl;

import static ru.studio.server.constaint.Constaint.JDBC_URL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.androidstudio.model.Order;
import com.example.androidstudio.model.service.Service;
import com.example.androidstudio.model.service.ServiceDate;
import com.example.androidstudio.model.service.ServiceRepair;
import com.example.androidstudio.model.service.ServiceSewing;
import com.example.androidstudio.model.table.TableDataOrder;

import ru.studio.server.constaint.Constaint;
import ru.studio.server.dao.OrderDao;

/**
 * @author Angelina Kuzmina
 * Created on 12/29/18
 */
public class OrderDaoImpl implements OrderDao
{
	public void saveServiceDate(ServiceDate serviceDate)
	{
		String SQL = "INSERT INTO servicedates (\"measurements\", \"modeling\", \"pattern\", \"stitching\") VALUES (?,?,?,?)";

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

	@Override
	public void removeServiceDate(int serviceDateId)
	{
		String SQL = "delete from servicedates WHERE id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{
			pstmt.setInt(1, serviceDateId);
			pstmt.executeUpdate();
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void updateService(Service service, Integer id)
	{
		service.setId(getOrderById(id).getServiceId());

		String serviceClassName = service.getClass().getSimpleName();
		String SQL = "UPDATE services set \"quantity\"=?, \"servicetype_id\"=?, \"servicedate_id\"=?, \"repairtype_id\"=? where id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS))
		{
			prepareServiceQueryParameter(service, serviceClassName, pstmt);

			pstmt.executeUpdate();
			service.setId(getModelId(serviceClassName, pstmt));
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}


	public void saveService(Service service)
	{
		String serviceClassName = service.getClass().getSimpleName();
		String SQL = "INSERT INTO services (\"quantity\", \"servicetype_id\", \"servicedate_id\", \"repairtype_id\" ) VALUES (?,?,?,?)";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS))
		{
			prepareServiceQueryParameter(service, serviceClassName, pstmt);

			pstmt.executeUpdate();

			service.setId(getModelId(serviceClassName, pstmt));
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	private void prepareServiceQueryParameter(Service service, String serviceClassName, PreparedStatement pstmt) throws SQLException
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
		if (service.getId() != null)
		{
			pstmt.setInt(5, service.getId());
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
		String SQL = "INSERT INTO orders " +
						"(\"tailor_assignment\", " +
						"\"user_id_manager\", " +
						"\"user_id_client\", " +
						"\"user_id_tailor\", " +
						"\"service_id\", " +
						"\"clother_type_id\", " +
						"\"cost\", " +
						"\"complete\",  " +
						"\"given_out\") " +
						"VALUES (?,?,?,?,?,?,?,?,?)";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS))
		{

			prepareOrderQueryPrameter(order, pstmt);

			pstmt.executeUpdate();

			order.setId(getModelId("Order", pstmt));
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void updateOrder(Order order)
	{
		String SQL = "UPDATE orders SET" +
						"\"tailor_assignment\" = ?, " +
						"\"user_id_manager\" = ?, " +
						"\"user_id_client\" = ?, " +
						"\"user_id_tailor\" = ?, " +
						"\"service_id\" = ?, " +
						"\"clother_type_id\" = ?, " +
						"\"cost\" = ?, " +
						"\"complete\" = ?,  " +
						"\"given_out\" = ? " +
						"WHERE id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS))
		{
			prepareOrderQueryPrameter(order, pstmt);
			pstmt.executeUpdate();
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void removeOrder(Integer id)
	{
		String SQL = "delete from orders WHERE id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	private void prepareOrderQueryPrameter(Order order, PreparedStatement pstmt) throws SQLException
	{
		pstmt.setBoolean(1, order.getTailorAssignment());
		pstmt.setInt(2, 2);
		//			pstmt.setInt(2, order.getManager().getId());
		pstmt.setInt(3, order.getClient().getId());
		//			pstmt.setInt(4, order.getTailor().getId());
		pstmt.setInt(4, 3);

		pstmt.setInt(5, order.getService().getId());
		pstmt.setInt(6, order.getClothesType().getId());
		pstmt.setDouble(7, order.getCost());
		pstmt.setBoolean(8, order.getComplete());
		pstmt.setBoolean(9, order.getGivenOut());

		if (order.getId() != null)
		{
			pstmt.setInt(10, order.getId());
		}
	}

	@Override
	public List<TableDataOrder> getOrdersByUserId(Integer id)
	{
		List<TableDataOrder> tableDataOrders = new ArrayList<>();

		getRepairOrdersByUserId(id, tableDataOrders);
		getSewingOrdersByUserId(id, tableDataOrders);
		return tableDataOrders;
	}

	private void getSewingOrdersByUserId(Integer id, List<TableDataOrder> tableDataOrders)
	{
		String SQL =
						"select o.id order_id, o.tailor_assignment, u.login, c.name clothe_name, c.id clothe_id, st.name servicetype_name, st.id servicetype_id, s.id service_id, sd.id servicedate_id, sd.measurements, sd.modeling, sd.pattern, sd.stitching, o.cost, o.complete, o.given_out from orders o inner join services s on service_id = s.id inner join users u on user_id_client = u.id inner join clothetypes as c on o.clother_type_id = c.id inner join servicedates sd on s.servicedate_id = sd.id inner join servicetypes st on s.servicetype_id = st.id where u.id = ? and sd.id <> 0 order by o.id ASC";


		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				TableDataOrder tableDataOrder = new TableDataOrder();
				tableDataOrder.setClientLogin(rs.getString("login"));
				tableDataOrder.setId(rs.getInt("order_id"));
				tableDataOrder.setTailorAssignment(rs.getBoolean("tailor_assignment"));
				tableDataOrder.setClotherType(rs.getString("clothe_name"));
				tableDataOrder.setClotherTypeId(rs.getInt("clothe_id"));
				tableDataOrder.setServiceType(rs.getString("servicetype_name"));
				tableDataOrder.setServiceTypeId(rs.getInt("servicetype_id"));
				tableDataOrder.setServiceId(rs.getInt("service_id"));
				tableDataOrder.setRepairType("-");
				tableDataOrder.setServiceDateId(rs.getInt("servicedate_id"));
				tableDataOrder.setMeasurements(rs.getString("measurements"));
				tableDataOrder.setModeling(rs.getString("modeling"));
				tableDataOrder.setPattern(rs.getString("pattern"));
				tableDataOrder.setStitching(rs.getString("stitching"));
				tableDataOrder.setCost(rs.getDouble("cost"));
				tableDataOrder.setComplete(rs.getBoolean("complete"));
				tableDataOrder.setGivenOut(rs.getBoolean("given_out"));
				tableDataOrders.add(tableDataOrder);
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	private void getRepairOrdersByUserId(Integer id, List<TableDataOrder> tableDataOrders)
	{
		String SQL =
						"select o.id order_id, o.tailor_assignment, u.login, c.name clothe_name, c.id clothe_id, st.name servicetype_name, st.id servicetype_id, s.id service_id, rt.name repairtype_name, rt.id repaiertype_id, o.cost, o.complete, o.given_out from orders o inner join services s on o.service_id = s.id inner join users u on o.user_id_client = u.id inner join clothetypes as c on o.clother_type_id = c.id inner join repairtypes rt on s.repairtype_id = rt.id inner join servicetypes st on s.servicetype_id = st.id where u.id = ? and s.repairtype_id <> 0 order by o.id ASC";


		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				TableDataOrder tableDataOrder = new TableDataOrder();
				tableDataOrder.setClientLogin(rs.getString("login"));
				tableDataOrder.setId(rs.getInt("order_id"));
				tableDataOrder.setTailorAssignment(rs.getBoolean("tailor_assignment"));
				tableDataOrder.setClotherType(rs.getString("clothe_name"));
				tableDataOrder.setClotherTypeId(rs.getInt("clothe_id"));
				tableDataOrder.setServiceId(rs.getInt("service_id"));
				tableDataOrder.setServiceType(rs.getString("servicetype_name"));
				tableDataOrder.setServiceTypeId(rs.getInt("servicetype_id"));
				tableDataOrder.setRepairType(rs.getString("repairtype_name"));
				tableDataOrder.setRepairTypeId(rs.getInt("repaiertype_id"));
				tableDataOrder.setMeasurements("-");
				tableDataOrder.setModeling("-");
				tableDataOrder.setPattern("-");
				tableDataOrder.setStitching("-");
				tableDataOrder.setCost(rs.getDouble("cost"));
				tableDataOrder.setComplete(rs.getBoolean("complete"));
				tableDataOrder.setGivenOut(rs.getBoolean("given_out"));
				tableDataOrders.add(tableDataOrder);
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public TableDataOrder getOrderById(Integer id)
	{
		List<TableDataOrder> tableDataOrders = new ArrayList<>();
		getRepairOrdersByOrderId(id, tableDataOrders);
		getSewingOrdersByOrderId(id, tableDataOrders);
		return tableDataOrders.size() > 0 ? tableDataOrders.get(0) : null;
	}

	private void getSewingOrdersByOrderId(Integer id, List<TableDataOrder> tableDataOrders)
	{
		String SQL =
						"select o.id order_id, o.tailor_assignment, u.login, c.name clothe_name, c.id clothe_id, st.name servicetype_name, st.id servicetype_id, s.id service_id, sd.id servicedate_id, sd.measurements, sd.modeling, sd.pattern, sd.stitching, o.cost, o.complete, o.given_out from orders o inner join services s on service_id = s.id inner join users u on user_id_client = u.id inner join clothetypes as c on o.clother_type_id = c.id inner join servicedates sd on s.servicedate_id = sd.id inner join servicetypes st on s.servicetype_id = st.id where o.id = ? and sd.id <> 0 order by o.id ASC";


		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				TableDataOrder tableDataOrder = new TableDataOrder();
				tableDataOrder.setClientLogin(rs.getString("login"));
				tableDataOrder.setId(rs.getInt("order_id"));
				tableDataOrder.setTailorAssignment(rs.getBoolean("tailor_assignment"));
				tableDataOrder.setClotherType(rs.getString("clothe_name"));
				tableDataOrder.setClotherTypeId(rs.getInt("clothe_id"));
				tableDataOrder.setServiceType(rs.getString("servicetype_name"));
				tableDataOrder.setServiceTypeId(rs.getInt("servicetype_id"));
				tableDataOrder.setServiceId(rs.getInt("service_id"));
				tableDataOrder.setRepairType("-");
				tableDataOrder.setServiceDateId(rs.getInt("servicedate_id"));
				tableDataOrder.setMeasurements(rs.getString("measurements"));
				tableDataOrder.setModeling(rs.getString("modeling"));
				tableDataOrder.setPattern(rs.getString("pattern"));
				tableDataOrder.setStitching(rs.getString("stitching"));
				tableDataOrder.setCost(rs.getDouble("cost"));
				tableDataOrder.setComplete(rs.getBoolean("complete"));
				tableDataOrder.setGivenOut(rs.getBoolean("given_out"));
				tableDataOrders.add(tableDataOrder);
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	private void getRepairOrdersByOrderId(Integer id, List<TableDataOrder> tableDataOrders)
	{
		String SQL =
						"select o.id order_id, o.tailor_assignment, u.login, c.name clothe_name, c.id clothe_id, st.name servicetype_name, st.id servicetype_id, s.id service_id, rt.name repairtype_name, rt.id repaiertype_id, o.cost, o.complete, o.given_out from orders o inner join services s on o.service_id = s.id inner join users u on o.user_id_client = u.id inner join clothetypes as c on o.clother_type_id = c.id inner join repairtypes rt on s.repairtype_id = rt.id inner join servicetypes st on s.servicetype_id = st.id where o.id = ? and s.repairtype_id <> 0 order by o.id ASC";


		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				TableDataOrder tableDataOrder = new TableDataOrder();
				tableDataOrder.setClientLogin(rs.getString("login"));
				tableDataOrder.setId(rs.getInt("order_id"));
				tableDataOrder.setTailorAssignment(rs.getBoolean("tailor_assignment"));
				tableDataOrder.setClotherType(rs.getString("clothe_name"));
				tableDataOrder.setClotherTypeId(rs.getInt("clothe_id"));
				tableDataOrder.setServiceId(rs.getInt("service_id"));
				tableDataOrder.setServiceType(rs.getString("servicetype_name"));
				tableDataOrder.setServiceTypeId(rs.getInt("servicetype_id"));
				tableDataOrder.setRepairType(rs.getString("repairtype_name"));
				tableDataOrder.setRepairTypeId(rs.getInt("repaiertype_id"));
				tableDataOrder.setMeasurements("-");
				tableDataOrder.setModeling("-");
				tableDataOrder.setPattern("-");
				tableDataOrder.setStitching("-");
				tableDataOrder.setCost(rs.getDouble("cost"));
				tableDataOrder.setComplete(rs.getBoolean("complete"));
				tableDataOrder.setGivenOut(rs.getBoolean("given_out"));
				tableDataOrders.add(tableDataOrder);
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public Connection connect() throws SQLException
	{
		return DriverManager.getConnection(JDBC_URL);
	}
}
