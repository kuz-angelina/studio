package ru.studio.client.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.*;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.Order;
import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.service.*;
import ru.studio.client.app.Application;
import ru.studio.client.app.Studio;

@Getter
@Setter
public class AddOrderGUI extends JFrame implements ActionListener
{
	Application app;
	Studio studio;

	Integer orderId;
	Integer serviceId;

	JLabel service = new JLabel("Услуга");
	JComboBox cbServiceType = new JComboBox();
	JLabel clothingType = new JLabel("Тип одежды");
	JComboBox cbClotheType = new JComboBox();
	JLabel typeOfRepair = new JLabel("Тип ремонта");
	JComboBox cbRepairType = new JComboBox();

	JButton btCreateOrder = new JButton("Создать заказ");
	JButton btEditOrder = new JButton("Изменить заказ");

	public AddOrderGUI(String title)
	{
		super(title);
		this.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new GridLayout(3, 2));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1));

		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 10));

		cbClotheType.setEnabled(false);
		cbRepairType.setEnabled(false);

		cbServiceType.addActionListener(this);
		btCreateOrder.addActionListener(this);

		mainPanel.add(service);
		mainPanel.add(cbServiceType);
		mainPanel.add(clothingType);
		mainPanel.add(cbClotheType);
		mainPanel.add(typeOfRepair);
		mainPanel.add(cbRepairType);
		buttonPanel.add(btCreateOrder);

		add(mainPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);

		this.pack();
	}

	public AddOrderGUI(String title, int orderId, int serviceId)
	{
		super(title);
		this.setOrderId(orderId);
		this.setServiceId(serviceId);
		this.setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new GridLayout(3, 2));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1));

		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 10));

		cbClotheType.setEnabled(false);
		cbRepairType.setEnabled(false);

		cbServiceType.addActionListener(this);
		btEditOrder.addActionListener(this);

		mainPanel.add(service);
		mainPanel.add(cbServiceType);
		mainPanel.add(clothingType);
		mainPanel.add(cbClotheType);
		mainPanel.add(typeOfRepair);
		mainPanel.add(cbRepairType);
		buttonPanel.add(btEditOrder);

		add(mainPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);

		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == cbServiceType)
		{
			JComboBox jComboBox = ((JComboBox) e.getSource());
			Object object = jComboBox.getSelectedItem();
			if (object != null)
			{
				String selectedItem = jComboBox.getSelectedItem().toString();
				{
					switch (selectedItem)
					{
						case Application.SERVICE_TYPE_REPAIR:
							List<String> repairTypeName = studio.getAllServiceRepairTypeName();
							DefaultComboBoxModel model = new DefaultComboBoxModel(repairTypeName.toArray());
							this.getCbRepairType().setModel(model);
							cbRepairType.setEnabled(true);
							break;
						case Application.SERVICE_TYPE_SEWING:
							cbRepairType.setEnabled(false);
							break;
					}
					List<String> clothesTypeName = studio.getAllClothesTypeName();
					DefaultComboBoxModel model = new DefaultComboBoxModel(clothesTypeName.toArray());
					cbClotheType.setEnabled(true);
					this.getCbClotheType().setModel(model);
				}
			}
		}
		if (e.getSource() == btEditOrder){
			Order order = initOrder();
			order.setId(this.getOrderId());
			order.getService().setId(this.getServiceId());
			studio.updateOrder(order);
			app.getMainClientGUI().initTable();
			app.getDialog().setVisible(false);
		}
		if (e.getSource() == btCreateOrder)
		{
			Order order = initOrder();
			studio.saveOrder(order);
			app.getMainClientGUI().initTable();
			app.getDialog().setVisible(false);
		}
	}

	private Order initOrder()
	{
		Integer clotheId = cbClotheType.getSelectedIndex() + 1;
		ClotheType clotheType = studio.getClotheTypeById(clotheId);
		Integer serviceTypeId = cbServiceType.getSelectedIndex() + 1;
		ServiceType serviceType = studio.getServiceTypeByID(serviceTypeId);

		Service service = null;
		if (Application.SERVICE_TYPE_REPAIR.equals(serviceType.getName()))
		{
			ServiceRepair serviceRepair = new ServiceRepair();
			serviceRepair.setServiceType(serviceType);
			Integer repairTypeId = cbRepairType.getSelectedIndex() + 1;
			RepairType repairType = studio.getRepairTypeById(repairTypeId);
			serviceRepair.setRepairType(repairType);
			service = serviceRepair;
		}
		if (Application.SERVICE_TYPE_SEWING.equals(serviceType.getName()))
		{
			ServiceSewing serviceSewing = new ServiceSewing();
			serviceSewing.setServiceType(serviceType);
			ServiceDate serviceDate = new ServiceDate();
			serviceSewing.setServiceDate(serviceDate);
			service = serviceSewing;
		}

		service.setQuantity(5);

		Order order = new Order();
		order.setClient(app.getClient());
		order.setClothesType(clotheType);
		order.setService(service);

		order.setTailorAssignment(false);
		order.setCost(100.0);
		order.setComplete(false);
		order.setGivenOut(false);
		return order;
	}
}
