package ru.studio.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.studio.api.services.OrderService;
import ru.studio.server.service.OrderServiceImpl;

@WebServlet(urlPatterns = "/remove")
public class OrdersRemove extends HttpServlet
{
	private OrderService orderService = new OrderServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		int orderId = Integer.parseInt(req.getParameter("orderid"));

		orderService.removeOrder(orderId);

		req.getRequestDispatcher("/orders").forward(req, resp);
	}
}
