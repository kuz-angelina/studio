package ru.studio.client.service;

import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;

import ru.studio.api.services.OrderService;
import ru.studio.api.model.Order;

/**
 * @author Angelina Kuzmina
 */
public class OrderServiceImpl implements OrderService
{
	String url = "http://localhost:8080/order";
	HessianProxyFactory factory = new HessianProxyFactory();
	OrderService orderService;

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
	public List<Order> getOrdersByUserId(Integer userId)
	{
		return null;
	}

	@Override
	public List<Order> getAllOrder()
	{
		return null;
	}
}
