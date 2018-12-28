package ru.studio.client.gui;

import ru.studio.client.app.Application;

/**
 * @author Angelina Kuzmina
 */
public class StartApplication
{

	public static void main(String[] args)
	{
		Application app = new Application();
		app.createObject();
		app.injectObject();
		app.initGui(app.getMainClientGUI());
	}
}