package ru.studio.server.service;

import static ru.studio.server.constaint.Constaint.SERVICE_TYPE_REPAIR;
import static ru.studio.server.constaint.Constaint.SERVICE_TYPE_SEWING;

import java.util.List;

import com.caucho.hessian.server.HessianServlet;
import com.example.androidstudio.model.Order;
import com.example.androidstudio.model.UserDto;
import com.example.androidstudio.model.clothes.ClotheType;
import com.example.androidstudio.model.role.Client;
import com.example.androidstudio.model.service.*;
import com.example.androidstudio.model.table.TableDataOrder;
import com.example.androidstudio.services.OrderService;

import ru.studio.server.dao.AdvancedDao;
import ru.studio.server.dao.OrderDao;
import ru.studio.server.dao.UserDao;
import ru.studio.server.dao.impl.AdvancedDaoImpl;
import ru.studio.server.dao.impl.OrderDaoImpl;
import ru.studio.server.dao.impl.UserDaoImp;


/**
 * @author Angelina Kuzmina
 */

public class OrderServiceImpl extends HessianServlet implements OrderService
{
	private UserDao userDao = new UserDaoImp();
	private OrderDao orderDao = new OrderDaoImpl();
	private AdvancedDao advancedDao = new AdvancedDaoImpl();

	@Override
	public TableDataOrder getOrderById(Integer id)
	{
		return orderDao.getOrderById(id);
	}

	@Override
	public void removeServiceDate(int serviceDateId)
	{
		orderDao.removeServiceDate(serviceDateId);
	}

	@Override
	public void removeOrder(Integer id)
	{
		orderDao.removeOrder(id);
	}

	@Override
	public void saveOrder(TableDataOrder dataOrder)
	{
		Order order = initOrder(dataOrder.getId(), dataOrder.getClientLogin(), dataOrder.getClotherTypeId(), dataOrder.getRepairTypeId(), dataOrder.getServiceTypeId());
		Service service = order.getService();
		if (SERVICE_TYPE_SEWING.equals(service.getServiceType().getName()))
		{
			ServiceSewing serviceSewing = (ServiceSewing) service;
			orderDao.saveServiceDate(serviceSewing.getServiceDate());
		}
		orderDao.saveService(service);
		orderDao.saveOrder(order);
	}

	@Override
	public List<TableDataOrder> getOrdersByUserId(Integer clientId)
	{
		return orderDao.getOrdersByUserId(clientId);
	}

	@Override
	public void updateOrder(TableDataOrder dataOrder)
	{
		Order order = initOrder(dataOrder.getId(), dataOrder.getClientLogin(), dataOrder.getClotherTypeId(), dataOrder.getRepairTypeId(), dataOrder.getServiceTypeId());
		Service service = order.getService();
		if (SERVICE_TYPE_SEWING.equals(service.getServiceType().getName()))
		{
			ServiceSewing serviceSewing = (ServiceSewing) service;
			orderDao.saveServiceDate(serviceSewing.getServiceDate());
		}
		orderDao.updateService(service, order.getId());
		orderDao.updateOrder(order);
	}

	@Override
	public List<Order> getAllOrder()
	{
		return null;
	}

	@Override
	public Order initOrder(Integer orderId, String clientLogin, int clotheId, int repairTypeId, int serviceTypeId)
	{
		UserDto userDto = userDao.getUserByLogin(clientLogin);
		Client client = new Client(userDto);
//		Integer clotheId = cbClotheType.getSelectedIndex() + 1;
		ClotheType clotheType = advancedDao.getClotheTypeById(clotheId);
//		Integer serviceTypeId = cbServiceType.getSelectedIndex() + 1;
		ServiceType serviceType = advancedDao.getServiceTypeByID(serviceTypeId);

		Service service = null;
		if (SERVICE_TYPE_REPAIR.equals(serviceType.getName()))
		{
			ServiceRepair serviceRepair = new ServiceRepair();
			serviceRepair.setServiceType(serviceType);
//			Integer repairTypeId = cbRepairType.getSelectedIndex() + 1;
			RepairType repairType = advancedDao.getRepairTypeById(repairTypeId);
			serviceRepair.setRepairType(repairType);
			service = serviceRepair;
		}
		if (SERVICE_TYPE_SEWING.equals(serviceType.getName()))
		{
			ServiceSewing serviceSewing = new ServiceSewing();
			serviceSewing.setServiceType(serviceType);
			ServiceDate serviceDate = new ServiceDate();
			serviceSewing.setServiceDate(serviceDate);
			service = serviceSewing;
		}

		service.setQuantity(5);

		Order order = new Order();
		if (orderId != null)
		{
			order.setId(orderId);
		}
		order.setClient(client);
		order.setClothesType(clotheType);
		order.setService(service);

		order.setTailorAssignment(false);
		order.setCost(100.0);
		order.setComplete(false);
		order.setGivenOut(false);
		return order;
	}
}
