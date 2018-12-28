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
public class RegGUI extends JFrame implements ActionListener
{
	Application app;
	Studio studio;

	JLabel registration = new JLabel("Регистрация: ");
	JLabel labelLogin = new JLabel("Ваш логин: ");
	JTextField loginField = new JTextField("");
	JLabel labelName = new JLabel("Ваше имя или название организации: ");
	JTextField nameField = new JTextField("");
	JLabel email = new JLabel("Ваш e-mail: ");
	JTextField emailField = new JTextField("");
	JLabel phone = new JLabel("Ваш телефон:");
	JTextField phoneField = new JTextField("");
	JLabel password = new JLabel("Пароль: ");
	JPasswordField passField = new JPasswordField("");
	JLabel password1 = new JLabel("Пароль: ");
	JPasswordField rePassField = new JPasswordField("");
	JButton btSend = new JButton("Отправить");

	public RegGUI(String title)
	{
		super(title);

		this.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new GridLayout(1, 1));
		JPanel panel = new JPanel(new GridLayout(6, 1));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1));

		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 10));

		mainPanel.add(registration);
		panel.add(labelLogin);
		panel.add(loginField);
		panel.add(labelName);
		panel.add(nameField);
		panel.add(email);
		panel.add(emailField);
		panel.add(phone);
		panel.add(phoneField);
		panel.add(password);
		panel.add(passField);
		panel.add(password1);
		panel.add(rePassField);
		buttonPanel.add(btSend);

		btSend.addActionListener(this);

		add(mainPanel, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btSend)
		{
			User user = app.validateAndCreateUser(this);
			if (user != null)
			{
				studio.saveUser(user);
				JOptionPane.showMessageDialog(null, "Вы успешно зарегистрировались, " + user.getLogin());
				app.checkRole(user);
			}
		}
	}
}
