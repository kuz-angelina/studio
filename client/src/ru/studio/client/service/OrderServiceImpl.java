package ru.studio.client.service;

import java.net.MalformedURLException;
import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;

import ru.studio.api.model.table.TableDataOrder;
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
	public void removeOrder(Integer orderId)
	{
		try
		{
			orderService = (OrderService) factory.create(OrderService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		orderService.removeOrder(orderId);
	}

	@Override
	public void removeServiceDate(int serviceDateId)
	{
		try
		{
			orderService = (OrderService) factory.create(OrderService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		orderService.removeServiceDate(serviceDateId);
	}

	@Override
	public Order getOrderById(Integer id)
	{
		return null;
	}

	@Override
	public void saveOrder(Order order)
	{
		try
		{
			orderService = (OrderService) factory.create(OrderService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		orderService.saveOrder(order);
	}

	@Override
	public List<TableDataOrder> getOrdersByUserId(Integer userId)
	{
		try
		{
			orderService = (OrderService) factory.create(OrderService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return orderService.getOrdersByUserId(userId);
	}

	@Override
	public List<Order> getAllOrder()
	{
		return null;
	}

	@Override
	public void updateOrder(Order order)
	{
		try
		{
			orderService = (OrderService) factory.create(OrderService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		orderService.updateOrder(order);
	}
}
