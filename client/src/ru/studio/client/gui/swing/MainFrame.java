package ru.studio.client.gui.swing;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.*;

/**
 * @author Angelina Kuzmina
 * Created on 05.12.18
 */

public class MainFrame extends JFrame
{

	private JButton studioButton = new JButton("Ателье");
	private JButton sewingButton = new JButton("Пошив одежды");
	private JButton repairsButton = new JButton("Ремонт одежды");

	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel panel = new JPanel(new GridLayout(3, 1));

	private StudioTab tabMain = new StudioTab();
	private StudioTab tabCompany = new StudioTab();
	private StudioTab addOrder = new StudioTab();
	private StudioTab tabVacncy = new StudioTab();
	private StudioTab tabContacty = new StudioTab();


	public MainFrame(String title) throws HeadlessException
	{
		super(title);

		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);

		setLayout(new BorderLayout());

		tabbedPane.addTab("Главная", tabMain);
		tabbedPane.addTab("О компании", tabCompany);
		tabbedPane.addTab("Добавить заказ", addOrder);
		tabbedPane.addTab("Вакансии", tabVacncy);
		tabbedPane.addTab("Контакты", tabContacty);


		panel.add(studioButton);
		panel.add(sewingButton);
		panel.add(repairsButton);

		add(tabbedPane, BorderLayout.CENTER);
		add(panel, BorderLayout.WEST);

	}
}
