package com.example.androidstudio.services;

import java.util.List;

import com.example.androidstudio.model.Order;
import com.example.androidstudio.model.table.TableDataOrder;

/**
 * @author Angelina Kuzmina
 */
public interface OrderService
{

	TableDataOrder getOrderById(Integer id);

	void saveOrder(TableDataOrder dataOrder);

	List<TableDataOrder> getOrdersByUserId(Integer clientId);

	void updateOrder(TableDataOrder dataOrder);

	List<Order> getAllOrder();

	void removeOrder(Integer orderId);

	void removeServiceDate(int serviceDateId);

	Order initOrder(Integer orderId, String clientLogin, int clotheId, int repairTypeId, int serviceTypeId);
}
