package ru.studio.server.service;

import java.util.List;

import com.caucho.hessian.server.HessianServlet;

import ru.studio.api.model.Order;
import ru.studio.api.model.service.Service;
import ru.studio.api.model.service.ServiceSewing;
import ru.studio.api.services.OrderService;
import ru.studio.server.constaint.Constaint;
import ru.studio.server.dao.OrderDao;
import ru.studio.server.dao.impl.OrderDaoImpl;


/**
 * @author Angelina Kuzmina
 */

public class OrderServiceImpl extends HessianServlet implements OrderService
{
	OrderDao orderDao = new OrderDaoImpl();

	@Override
	public Order createOrder()
	{
		return null;
	}

	@Override
	public Order getOrderById(Integer id)
	{
		return null;
	}

	@Override
	public void saveOrder(Order order)
	{
		Service service = order.getService();
		if (Constaint.SERVICE_TYPE_SEWING.equals(service.getServiceType().getName()))
		{
			ServiceSewing serviceSewing = (ServiceSewing) service;
			orderDao.saveServiceDate(serviceSewing.getServiceDate());
		}
		orderDao.saveService(service);
		orderDao.saveOrder(order);
	}

	@Override
	public List<Order> getOrdersByUserId(Integer clientId)
	{
		return null;
	}

	@Override
	public List<Order> getAllOrder()
	{
		return null;
	}
}
