package ru.studio.client.gui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.table.TableDataOrder;
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

	JLabel orders = new JLabel("Список заказов: ");

	JTable table = new JTable();

	public void initTable()
	{
		List<TableDataOrder> list = studio.getOrdersByUserId(app.getClient().getId());

		String[] columnNames = {
						"№",
						"Вид сервиса",
						"Вид Ремонта",
						"Вид одежды",
						"Снятие мерок",
						"Моделирование",
						"Выкройка",
						"Шитье",
						"Стоимость",
						"Заказ готов",
						"Заказ выдан"
		};

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		model.addRow(columnNames);


		for (TableDataOrder tableDataOrder : list)
		{
			Object[] o = new Object[11];
			o[0] = tableDataOrder.getId();
			o[1] = tableDataOrder.getServiceType();
			o[2] = tableDataOrder.getRepairType();
			o[3] = tableDataOrder.getClotherType();
			o[4] = tableDataOrder.getMeasurements();
			o[5] = tableDataOrder.getModeling();
			o[6] = tableDataOrder.getPattern();
			o[7] = tableDataOrder.getStitching();
			o[8] = tableDataOrder.getCost();
			if (tableDataOrder.getComplete())
			{
				o[9] = "Заказ готов";
			}
			else
			{
				o[9] = "Заказ не готов";
			}
			if (tableDataOrder.getGivenOut())
			{
				o[10] = "Заказ выдан";
			}
			else
			{
				o[10] = "Заказ не выдан";
			}
			model.addRow(o);
		}

		app.getMainClientGUI().getTable().setModel(model);
	}

	public MainClientGUI(String title) throws HeadlessException
	{
		super(title);

		setLayout(new BorderLayout());

		//		initTable();
		initOrdersTab();
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
		this.setSize(800, 450);

		btLogin.addActionListener(this);
		btAddOrder.addActionListener(this);
		btEditOrder.addActionListener(this);
		btDelOrder.addActionListener(this);

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
		btLogin.setLocation(670, 0);


		tabMainPanel.add(btStudio);
		tabMainPanel.add(btRepair);
		tabMainPanel.add(btSewing);
		tabMainPanel.add(btLogin);
	}

	private void initOrdersTab()
	{
		tabOrderPanel.setLayout(null);
		btAddOrder.setSize(110, 40);
		btEditOrder.setSize(110, 40);
		btDelOrder.setSize(110, 40);

		btAddOrder.setLocation(10, 330);
		btEditOrder.setLocation(130, 330);
		btDelOrder.setLocation(250, 330);

		btAddOrder.setEnabled(false);
		btAddOrder.setText("Добавить");
		btEditOrder.setEnabled(false);
		btEditOrder.setText("Изменить");
		btDelOrder.setEnabled(false);
		btDelOrder.setText("Удалить");

		table.setSize(800, 290);
		table.setLocation(10, 10);

		tabOrderPanel.add(btAddOrder);
		tabOrderPanel.add(btEditOrder);
		tabOrderPanel.add(btDelOrder);
		tabOrderPanel.add(table);
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
		if (e.getSource() == btEditOrder)
		{
			int id = app.getMainClientGUI().getTable().getSelectedRow();
			List<TableDataOrder> list = studio.getOrdersByUserId(app.getClient().getId());
			TableDataOrder tableDataOrder = list.get(id - 1);
			int orderId = tableDataOrder.getId();
			int serviceId = tableDataOrder.getServiceId();
			app.getEditOrderGUI().setOrderId(orderId);
			app.getEditOrderGUI().setServiceId(serviceId);
			List<String> services = studio.getAllServicesTypeName();
			DefaultComboBoxModel model = new DefaultComboBoxModel(services.toArray());
			app.getEditOrderGUI().getCbServiceType().setModel(model);
			app.getEditOrderGUI().getCbServiceType().setSelectedIndex(-1);
			app.initGui(app.getEditOrderGUI());
		}
		if (e.getSource() == btDelOrder)
		{
			int id = app.getMainClientGUI().getTable().getSelectedRow();
			List<TableDataOrder> list = studio.getOrdersByUserId(app.getClient().getId());
			TableDataOrder tableDataOrder = list.get(id - 1);

			if (Application.SERVICE_TYPE_REPAIR.equals(tableDataOrder.getServiceType()))
			{
				int serviceId = tableDataOrder.getServiceId();
				studio.removeOrder(serviceId);
			}
			if (Application.SERVICE_TYPE_SEWING.equals(tableDataOrder.getServiceType()))
			{
				int serviceDateId = tableDataOrder.getServiceDateId();
				studio.removeServiceDate(serviceDateId);
			}
			this.initTable();
		}

	}
}
