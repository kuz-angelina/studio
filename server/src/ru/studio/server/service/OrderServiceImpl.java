package ru.studio.server.service;

import java.util.List;

import com.caucho.hessian.server.HessianServlet;

import ru.studio.api.model.Order;
import ru.studio.api.services.OrderService;


/**
 * @author Angelina Kuzmina
 */

public class OrderServiceImpl extends HessianServlet implements OrderService
{
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
