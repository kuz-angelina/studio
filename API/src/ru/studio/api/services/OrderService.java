package ru.studio.api.services;

import java.util.List;

import ru.studio.api.model.Order;
import ru.studio.api.model.table.TableDataOrder;

/**
 * @author Angelina Kuzmina
 */
public interface OrderService
{

	Order getOrderById(Integer id);

	void saveOrder(Order order);

	List<TableDataOrder> getOrdersByUserId(Integer clientId);

	List<Order> getAllOrder();

	void updateOrder(Order order);

	void removeOrder(Integer orderId);

	void removeServiceDate(int serviceDateId);
}
