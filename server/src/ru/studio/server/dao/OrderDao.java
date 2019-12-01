

package ru.studio.server.dao;

import java.util.List;

import com.example.androidstudio.model.Order;
import com.example.androidstudio.model.service.Service;
import com.example.androidstudio.model.service.ServiceDate;
import com.example.androidstudio.model.table.TableDataOrder;

/**
 * @author Angelina Kuzmina
 * Created on 12/29/18
 */
public interface OrderDao
{
	void saveOrder(Order order);

	void saveService(Service service);

	void saveServiceDate(ServiceDate serviceDate);

	List<TableDataOrder> getOrdersByUserId(Integer id);

	TableDataOrder getOrderById(Integer id);

	void updateService(Service service, Integer id);

	void updateOrder(Order order);

	void removeOrder(Integer orderId);

	void removeServiceDate(int serviceDateId);
}
