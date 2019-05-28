

package ru.studio.server.dao;

import java.util.List;

import ru.studio.api.model.Order;
import ru.studio.api.model.service.Service;
import ru.studio.api.model.service.ServiceDate;
import ru.studio.api.model.table.TableDataOrder;

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
