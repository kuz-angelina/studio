
package ru.studio.web.controllers;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.studio.api.model.role.Client;
import ru.studio.api.model.table.TableDataOrder;
import ru.studio.api.services.OrderService;
import ru.studio.server.service.OrderServiceImpl;

@WebServlet(urlPatterns = "/orders")
public class Orders extends HttpServlet
{
	private OrderService orderService = new OrderServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		Client client = (Client) session.getAttribute("user");

		if (client != null)
		{
			List<TableDataOrder> orders = orderService.getOrdersByUserId(client.getId());
			final List<TableDataOrder> sortedByID = orders.stream().sorted(Comparator.comparingInt(TableDataOrder::getId)).collect(Collectors.toList());
			session.setAttribute("orders", sortedByID);
			req.getRequestDispatcher("pages/orders.jsp").forward(req, resp);
		}
		else
		{
			req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
		}


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		Client client = (Client) session.getAttribute("user");

		if (client != null)
		{
			List<TableDataOrder> orders = orderService.getOrdersByUserId(client.getId());
			final List<TableDataOrder> sortedByID = orders.stream().sorted(Comparator.comparingInt(TableDataOrder::getId)).collect(Collectors.toList());
			session.setAttribute("orders", sortedByID);
			req.getRequestDispatcher("pages/orders.jsp").forward(req, resp);
		}
		else
		{
			req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
		}


	}
}
