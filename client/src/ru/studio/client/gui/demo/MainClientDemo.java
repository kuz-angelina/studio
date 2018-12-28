
package ru.studio.client.gui.demo;

import ru.studio.client.app.Application;
import ru.studio.client.gui.MainClientGUI;

/**
 * @author Angelina Kuzmina
 * Created on 05.12.18
 */
public class MainClientDemo
{
	public static void main(String[] args)
	{
		MainClientGUI mainClientGUI = new MainClientGUI(Application.GUI_CLIENT);
		Application application = new Application();
		application.initGui(mainClientGUI);
	}
}
