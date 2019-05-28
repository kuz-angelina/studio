package ru.studio.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.studio.api.model.table.TableDataOrder;
import ru.studio.api.services.OrderService;
import ru.studio.server.service.OrderServiceImpl;


@WebServlet(urlPatterns = "/edit")
public class OrdersEdit extends HttpServlet
{
	private OrderService orderService = new OrderServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		int orderId = Integer.parseInt(req.getParameter("orderid"));
		TableDataOrder order = orderService.getOrderById(orderId);
		session.setAttribute("order", order);
		req.getRequestDispatcher("/orders").forward(req, resp);
	}
}