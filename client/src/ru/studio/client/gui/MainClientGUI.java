package ru.studio.client.gui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
public class MainClientGUI extends JFrame implements ActionListener
{
	Application app;
	Studio studio;

	private JButton btStudio = new JButton("Ателье");
	private JButton btSewing = new JButton("Пошив одежды");
	private JButton btRepair = new JButton("Ремонт одежды");
	private JButton btLogin = new JButton("Вход");
	private JButton btAddOrder = new JButton("Добавить");
	private JButton btEditOrder = new JButton("Изменить");
	private JButton btDelOrder = new JButton("Удалить");


	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel panel = new JPanel(new GridLayout(3, 1));
	private JPanel bpanel = new JPanel(new GridLayout(1, 1));


	private StudioTab tabCompany = new StudioTab();
	private StudioTab tabVacncy = new StudioTab();
	private StudioTab tabContacty = new StudioTab();

	JPanel tabOrderPanel = new JPanel();
	JPanel tabMainPanel = new JPanel();


	public MainClientGUI(String title) throws HeadlessException
	{
		super(title);

		setLayout(new BorderLayout());

		initTabOrde();
		initMainTab();

		tabbedPane.addTab("Главная", tabMainPanel);
		tabbedPane.addTab("О компании", tabCompany);
		tabbedPane.addTab("Заказы", tabOrderPanel);
		tabbedPane.addTab("Вакансии", tabVacncy);
		tabbedPane.addTab("Контакты", tabContacty);

		add(tabbedPane, BorderLayout.CENTER);
		add(panel, BorderLayout.WEST);
		add(bpanel, BorderLayout.EAST);

		this.pack();
		this.setSize(600, 450);

		btLogin.addActionListener(this);

	}

	private void initMainTab()
	{
		tabMainPanel.setLayout(null);
		btStudio.setSize(200, 40);
		btRepair.setSize(200, 40);
		btSewing.setSize(200, 40);
		btLogin.setSize(100, 40);
		btStudio.setLocation(10, 50);
		btRepair.setLocation(10, 150);
		btSewing.setLocation(10, 250);
		btLogin.setLocation(490, 0);

		btAddOrder.addActionListener(this);

		tabMainPanel.add(btStudio);
		tabMainPanel.add(btRepair);
		tabMainPanel.add(btSewing);
		tabMainPanel.add(btLogin);
	}

	private void initTabOrde()
	{
		tabOrderPanel.setLayout(null);
		btAddOrder.setSize(110, 40);
		btEditOrder.setSize(110, 40);
		btDelOrder.setSize(110, 40);

		btAddOrder.setLocation(10, 300);
		btEditOrder.setLocation(130, 300);
		btDelOrder.setLocation(250, 300);

		btAddOrder.setEnabled(false);
		btAddOrder.setText("Добавить");
		btEditOrder.setEnabled(false);
		btEditOrder.setText("Изменить");
		btDelOrder.setEnabled(false);
		btDelOrder.setText("Удалить");

		tabOrderPanel.add(btAddOrder);
		tabOrderPanel.add(btEditOrder);
		tabOrderPanel.add(btDelOrder);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btLogin)
		{
			app.getLoginGUI().getTextLogin().setText(null);
			app.getLoginGUI().getPass().setText(null);
			app.initGui(app.getLoginGUI());
		}
		if (e.getSource() == btAddOrder)
		{
			List<String> services = studio.getAllServicesTypeName();
			DefaultComboBoxModel model = new DefaultComboBoxModel(services.toArray());
			app.getAddOrderGUI().getCbServiceType().setModel(model);
			app.getAddOrderGUI().getCbServiceType().setSelectedIndex(-1);
			app.initGui(app.getAddOrderGUI());
		}
	}
}
