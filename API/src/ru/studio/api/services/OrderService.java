package ru.studio.api.services;

import java.util.List;

import ru.studio.api.model.Order;
import ru.studio.api.model.role.Client;
import ru.studio.api.model.table.TableDataOrder;

/**
 * @author Angelina Kuzmina
 */
public interface OrderService
{

	TableDataOrder getOrderById(Integer id);

	void saveOrder(Order order);

	List<TableDataOrder> getOrdersByUserId(Integer clientId);

	List<Order> getAllOrder();

	void updateOrder(Order order);

	void removeOrder(Integer orderId);

	void removeServiceDate(int serviceDateId);

	Order initOrder(Client client, int clotheId, int repairTypeId, int serviceTypeId);
}
