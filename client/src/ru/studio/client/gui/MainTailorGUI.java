package ru.studio.client.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Angelina Kuzmina
 * Created on 05.12.18
 */
@Getter
@Setter
public class MainTailorGUI extends JFrame
{
	public MainTailorGUI(String title)
	{
		super(title);
		JLabel listOrder = new JLabel("Список заказов: ");
		JButton information = new JButton("Информация");
		String[] columnNames = {
						"Номер",
						"ФИО",
						"Заказы"
		};
		String[][] data = {
						{"1", "Иванов Иван Иванович", "Button", ""},
						{"2", "Михеенко Алиса Дмитриевна", "Button", ""},
		};
		JTable table = new JTable(data, columnNames);

		this.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new GridLayout(2, 1));
		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 130, 10));

		mainPanel.add(listOrder);
		mainPanel.add(table);
		panel.add(information);

		add(mainPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);

		this.pack();
		this.setSize(600, 300);
	}
}

