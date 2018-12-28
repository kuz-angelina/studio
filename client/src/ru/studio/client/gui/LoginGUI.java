package ru.studio.client.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.role.User;
import ru.studio.client.app.Application;
import ru.studio.client.app.Studio;
/**
 * @author Angelina Kuzmina
 * Created on 05.12.18
 */
@Getter
@Setter
public class LoginGUI extends JFrame implements ActionListener
{
	Application app;
	Studio studio;

	JLabel label = new JLabel("Введите свои данные: ");
	JLabel login = new JLabel("Логин");
	JTextField textLogin = new JTextField(" ");
	JLabel password = new JLabel("Пароль");
	JPasswordField pass = new JPasswordField();
	JButton btLogin = new JButton("Войти");
	JButton btReg = new JButton("Регистрация");

	public LoginGUI(String title)
	{
		super(title);

		this.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new GridLayout(1, 1));
		JPanel panel = new JPanel(new GridLayout(7, 1));
		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 60, 10, 60));

		mainPanel.add(label);
		panel.add(login);
		panel.add(textLogin);
		panel.add(password);
		panel.add(pass);
		buttonPanel.add(btLogin);
		buttonPanel.add(btReg);

		add(mainPanel, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		btLogin.addActionListener(this);
		btReg.addActionListener(this);

		this.pack();
		this.setSize(300,250);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btLogin){
			boolean ifSuccess = false;
			User user = studio.login(this.getTextLogin().getText(), this.getPass().getText());

			if (user == null)
			{
				JOptionPane.showMessageDialog(null, "Логин/пароль не верен");
				ifSuccess = false;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Добро пожаловать, " + user.getLogin());
				ifSuccess = true;
			}
			if (ifSuccess)
			{
				app.checkRole(user);
			}
		}
		if (e.getSource() == btReg){
			app.initGui(app.getRegGUI());
		}
	}
}

