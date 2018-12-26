package ru.studio.api.services;

import java.util.List;

import ru.studio.api.model.Order;

/**
 * @author Angelina Kuzmina
 */
public interface OrderService
{

	Order createOrder();

	Order getOrderById(Integer id);

	void saveOrder(Order order);

	List<Order> getOrdersByUserId(Integer clientId);

	List<Order> getAllOrder();

}
