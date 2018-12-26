package ru.studio.client.gui;

import javax.swing.JPanel;

import ru.studio.client.app.Application;

/**
 * @author Angelina Kuzmina
 */
public class TailorGUI implements GUItabble
{
	private JPanel rootPanel;

	@Override
	public String getTitle()
	{
		return Application.GUI_TAILOR;
	}

	@Override
	public JPanel getRootPanel()
	{
		return rootPanel;
	}
}
