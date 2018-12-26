package ru.studio.client.gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;
import ru.studio.client.app.Application;
import ru.studio.client.app.Studio;
import ru.studio.api.model.role.User;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class RegGUI implements GUItabble
{
	private JPanel rootPanel;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField loginField;
	private JTextField passField;
	private JTextField rePassField;
	private JButton btReg;

	Application application;
	Studio studio;

	public RegGUI()
	{
		btReg.addActionListener(e -> {
			User user = application.validateAndCreateUser(this);
			if (user != null)
			{
				studio.saveUser(user);
				JOptionPane.showMessageDialog(null, "Вы успешно зарегистрировались, " + user.getLogin());
				application.checkRole(user);
			}
		});
	}

	@Override
	public String getTitle()
	{
		return Application.GUI_REG;
	}

	@Override
	public JPanel getRootPanel()
	{
		return rootPanel;
	}
}
