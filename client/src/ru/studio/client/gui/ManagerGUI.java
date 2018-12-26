package ru.studio.client.gui;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import ru.studio.client.app.Application;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class ManagerGUI implements GUItabble
{
	private JPanel rootPanel;

	@Override
	public String getTitle()
	{
		return Application.GUI_CLIENT;
	}

	@Override
	public JPanel getRootPanel()
	{
		return rootPanel;
	}
}
