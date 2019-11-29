package ru.studio.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.studio.api.model.role.Client;
import ru.studio.api.services.UserService;
import ru.studio.server.service.UserServiceImpl;

@WebServlet(urlPatterns = "/login")
public class Auth extends HttpServlet
{
	private UserService userService = new UserServiceImpl();
	private static final String MSG = "Не правильный логин/пароль";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		session.setAttribute("login", login);

		Client authClient = (Client) userService.getUserByLogin(login);

		if (authClient.getPassword() != null && authClient.getPassword().equals(password))
		{
			session.setAttribute("user", authClient);
			req.getRequestDispatcher("/orders").forward(req, resp);
		}
		else
		{
			resp.setContentType("text/html; charset=utf-8");
			req.setAttribute("msg", MSG);
			req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
		}
	}
}
