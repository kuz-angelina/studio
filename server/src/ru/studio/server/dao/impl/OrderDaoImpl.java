

package ru.studio.server.dao.impl;

import static ru.studio.server.constaint.Constaint.JDBC_URL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ru.studio.api.model.Order;
import ru.studio.api.model.service.Service;
import ru.studio.api.model.service.ServiceDate;
import ru.studio.api.model.service.ServiceRepair;
import ru.studio.api.model.service.ServiceSewing;
import ru.studio.api.model.table.TableDataOrder;
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
		String serviceClassName = service.getClass().getSimpleName();
		String SQL = "UPDATE services set \"quantity\"=?, \"service_type_id\"=?, \"service_date_id\"=?, \"repair_type_id\"=? where id = ?";

		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS))
		{
			prepareServiceQueryParameter(service, serviceClassName, pstmt);

			pstmt.executeUpdate();
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}


	public void saveService(Service service)
	{
		String serviceClassName = service.getClass().getSimpleName();
		String SQL = "INSERT INTO services (\"quantity\", \"service_type_id\", \"service_date_id\", \"repair_type_id\" ) VALUES (?,?,?,?)";

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
						"\"clothe_type_id\", " +
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
						"\"clothe_type_id\" = ?, " +
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
		String SQL = "delete from services WHERE id = ?";

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
						"select o.id order_id, o.tailor_assignment, c.name clothe_name, st.name servicetype_name, s.id service_id, sd.id servicedate_id, sd.measurements, sd.modeling, sd.pattern, sd.stitching, o.cost, o.complete, o.given_out from orders o inner join services s on service_id = s.id inner join users u on user_id_client = u.id inner join clothetypes as c on clothe_type_id = c.id inner join servicedates sd on service_date_id = sd.id inner join servicetypes st on service_type_id = st.id where u.id = ? and sd.id <> 0 order by o.id ASC";


		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				TableDataOrder tableDataOrder = new TableDataOrder();
				tableDataOrder.setId(rs.getInt("order_id"));
				tableDataOrder.setTailorAssignment(rs.getBoolean("tailor_assignment"));
				tableDataOrder.setClotherType(rs.getString("clothe_name"));
				tableDataOrder.setServiceType(rs.getString("servicetype_name"));
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
		String SQL = "select o.id order_id, o.tailor_assignment, c.name clothe_name, st.name servicetype_name, s.id service_id, rt.name repairtype_name, o.cost, o.complete, o.given_out from orders o inner join services s on service_id = s.id inner join users u on user_id_client = u.id inner join clothetypes as c on clothe_type_id = c.id inner join repairtypes rt on repair_type_id = rt.id inner join servicetypes st on service_type_id = st.id where u.id = ? and repair_type_id <> 0 order by o.id ASC";


		try (Connection conn = connect();
				 PreparedStatement pstmt = conn.prepareStatement(SQL))
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				TableDataOrder tableDataOrder = new TableDataOrder();
				tableDataOrder.setId(rs.getInt("order_id"));
				tableDataOrder.setTailorAssignment(rs.getBoolean("tailor_assignment"));
				tableDataOrder.setClotherType(rs.getString("clothe_name"));
				tableDataOrder.setServiceId(rs.getInt("service_id"));
				tableDataOrder.setServiceType(rs.getString("servicetype_name"));
				tableDataOrder.setRepairType(rs.getString("repairtype_name"));
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
	public Order getOrderById(Integer id)
	{
		return null;
	}


	public Connection connect() throws SQLException
	{
		return DriverManager.getConnection(JDBC_URL);
	}
}
