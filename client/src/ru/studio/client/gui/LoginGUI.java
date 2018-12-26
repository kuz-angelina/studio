package ru.studio.client.gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.role.User;
import ru.studio.client.app.Application;
import ru.studio.client.app.Studio;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class LoginGUI implements GUItabble
{
	private JTextField loginField;
	private JTextField passField;
	private JButton btLogin;
	private JButton btReg;
	private JPanel rootPanel;

	private Application application;
	private Studio studio;

	public LoginGUI()
	{

		btLogin.addActionListener(e -> {
			boolean ifSuccess = false;
			User user = studio.login(this.getLoginField().getText(), this.getPassField().getText());

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
				application.checkRole(user);
			}
		});

		btReg.addActionListener(e -> {
			application.initGui(application.getRegGUI());
		});
	}

	@Override
	public String getTitle()
	{
		return "Login";
	}

	@Override
	public JPanel getRootPanel()
	{
		return rootPanel;
	}

}
