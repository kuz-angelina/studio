package ru.studio.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.studio.api.model.Order;
import ru.studio.api.model.role.Client;
import ru.studio.api.model.service.Service;
import ru.studio.api.model.service.ServiceRepair;
import ru.studio.api.model.table.TableDataOrder;
import ru.studio.api.services.OrderService;
import ru.studio.server.service.OrderServiceImpl;

@WebServlet(urlPatterns = "/add")
public class OrdersAdd extends HttpServlet
{
	private OrderService orderService = new OrderServiceImpl();
	private static final String EDIT = "Изменить";
	private static final String ADD = "Добавить";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		Client client = (Client) session.getAttribute("user");

		final String serviceTypeStr = req.getParameter("serviceType");
		int serviceType = 0;
		if (serviceTypeStr != null)
		{
			serviceType = Integer.parseInt(serviceTypeStr);
		}

		String repairTypeStr = req.getParameter("repairType");
		int repairType = 0;
		if (repairTypeStr != null)
		{
			repairType = Integer.parseInt(repairTypeStr);
		}

		final String clotherTypeStr = req.getParameter("clotherType");
		int clotherType = 0;
		if (clotherTypeStr != null)
		{
			clotherType = Integer.parseInt(clotherTypeStr);
		}
		String action = req.getParameter("action");

		final Order order = orderService.initOrder(client, clotherType, repairType, serviceType);


		if (action.equals(ADD))
		{
			orderService.saveOrder(order);
		}
		if (action.equals(EDIT))
		{
			int orderId = Integer.parseInt(req.getParameter("orderId"));
			order.setId(orderId);
			orderService.updateOrder(order);
			final TableDataOrder table = convertOrderToTableData(order);
			session.setAttribute("order", table);
		}

		req.getRequestDispatcher("/orders").forward(req, resp);
	}

	private TableDataOrder convertOrderToTableData(Order order)
	{
		Service service = order.getService();
		TableDataOrder table = new TableDataOrder();
		table.setId(order.getId());
		table.setServiceType(service.getServiceType().getName());
		table.setServiceTypeId(service.getServiceType().getId());
		table.setClotherType(order.getClothesType().getName());
		table.setClotherTypeId(order.getClothesType().getId());

		if (service.getClass().getSimpleName().equals("ServiceRepair"))
		{
			ServiceRepair serviceRepair = (ServiceRepair) service;
			table.setRepairType(serviceRepair.getRepairType().getName());
			table.setRepairTypeId(serviceRepair.getId());
		}

		//		if (service.getClass().getSimpleName().equals("ServiceSewing"))
		//		{
		//			ServiceSewing serviceSewing = (ServiceSewing) service;
		//		}
		//
		return table;
	}


}
