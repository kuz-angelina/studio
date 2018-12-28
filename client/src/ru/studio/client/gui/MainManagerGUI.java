package ru.studio.client.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import lombok.Getter;
import lombok.Setter;
import ru.studio.client.app.Application;
import ru.studio.client.app.Studio;
/**
 * @author Angelina Kuzmina
 * Created on 05.12.18
 */
@Getter
@Setter
public class MainManagerGUI extends JFrame
{
	Application app;
	Studio studio;

	JButton button = new JButton("Офромить");
	JButton button1 = new JButton("Офромить");

	JTabbedPane tabbedPane = new JTabbedPane();
	StudioTab tabCheckout = new StudioTab();
	StudioTab tailorData = new StudioTab();
	StudioTab listOrder = new StudioTab();
	JLabel orders = new JLabel("Список заказов: ");

	String[] columnNames = {
					"№",
					"ФИО",
					"Телефон",
					"Что делать",
					"Тип одежды",
					"Сформировать заказ"

	};
	String[][] data = {
					{"1", "Соловьева А.Д.","8-996-345-13-54","Пошив","Юбка", "Button"},
					{"2", "Иванов И.И.","8-923-234-53-21","Ремонт","Куртка", "Button"},
	};
	JTable table = new JTable(data,columnNames);


	public MainManagerGUI(String title)
	{
		super(title);

		this.setLayout(new BorderLayout());

		tabbedPane.addTab("Оформить заказ", tabCheckout);
		tabbedPane.addTab("Данные о портных", tailorData);
		tabbedPane.addTab("Список заказов",listOrder);

		JPanel panel = new JPanel(new GridLayout(2,1));

		panel.add(orders);
		panel.add(table);

		this.add(tabbedPane,BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);

		this.pack();
		this.setSize(600, 300);
	}
}
