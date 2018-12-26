package ru.studio.client.gui;


import static ru.studio.client.app.Application.SERVICE_TYPE_REPAIR;
import static ru.studio.client.app.Application.SERVICE_TYPE_SEWING;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.service.*;
import ru.studio.client.app.Application;
import ru.studio.api.model.Order;
import ru.studio.client.app.Studio;
import ru.studio.api.model.clothes.ClotheType;


/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
@SuppressWarnings("unchecked")
public class AddOrderGUI implements GUItabble
{
	private JPanel rootPanel;
	private JComboBox cbClotheType;
	private JComboBox cbRepairType;
	private JButton btCreateOrder;
	private JComboBox cbServiceType;
	private Integer width;
	private Integer height;

	Application app;
	Studio studio;


	public AddOrderGUI()
	{

		cbServiceType.addActionListener(e -> {
			JComboBox jComboBox = ((JComboBox) e.getSource());
			Object object = jComboBox.getSelectedItem();
			if (object != null)
			{
				String selectedItem = jComboBox.getSelectedItem().toString();
				{
					switch (selectedItem)
					{
						case SERVICE_TYPE_REPAIR:
							List<String> repairTypeName = studio.getAllServiceRepairTypeName();
							DefaultComboBoxModel model = new DefaultComboBoxModel(repairTypeName.toArray());
							app.getAddOrderGUI().getCbRepairType().setModel(model);
							cbRepairType.setEnabled(true);
							break;
						case SERVICE_TYPE_SEWING:
							cbRepairType.setEnabled(false);
							break;
					}
					List<String> clothesTypeName = studio.getAllClothesTypeName();
					DefaultComboBoxModel model = new DefaultComboBoxModel(clothesTypeName.toArray());
					app.getAddOrderGUI().getCbClotheType().setModel(model);
				}
			}
		});

		btCreateOrder.addActionListener(e -> {
			Integer clotheId = cbClotheType.getSelectedIndex();
			ClotheType clotheType = studio.getClotheTypeById(clotheId);
			Integer serviceTypeId = cbServiceType.getSelectedIndex();
			ServiceType serviceType = studio.getServiceTypeByID(serviceTypeId);

			Service service = null;
			if (SERVICE_TYPE_REPAIR.equals(serviceType.getName()))
			{
				ServiceRepair serviceRepair = new ServiceRepair();
				serviceRepair.setServiceType(serviceType);
				Integer repairTypeId = cbRepairType.getSelectedIndex();
				RepairType repairType = studio.getRepairTypeById(repairTypeId);
				serviceRepair.setRepairType(repairType);
				service = serviceRepair;
			}
			if (SERVICE_TYPE_SEWING.equals(serviceType.getName()))
			{
				ServiceSewing serviceSewing = new ServiceSewing();
				serviceSewing.setServiceType(serviceType);
				ServiceDate serviceDate = new ServiceDate();
				serviceSewing.setServiceDate(serviceDate);
				service = serviceSewing;
			}

			Order order = new Order();
			order.setClient(app.getClient());
			order.setClothesType(clotheType);
			order.setService(service);
			studio.saveOrder(order);
		});
	}


	@Override
	public String getTitle()
	{
		return Application.GUI_ADD_ORDER;
	}

	@Override
	public JPanel getRootPanel()
	{
		return rootPanel;
	}
}
