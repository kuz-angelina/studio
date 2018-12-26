package ru.studio.client.gui;

import java.util.List;

import javax.swing.*;

import lombok.Getter;
import lombok.Setter;
import ru.studio.client.app.Application;
import ru.studio.client.app.Studio;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class ClientGUI implements GUItabble
{

	Application app;
	Studio studio;

	private JPanel rootPanel;
	private JPanel tabMain;
	private JPanel tabGetOrder;
	private JButton btStudio;
	private JButton btSewing;
	private JButton btRepair;
	private JButton btLogin;
	private JTable table1;
	private JTable table2;
	private JButton btAddOrder;
	private JButton btDelTicket;
	private JButton btEditTIcket;

	public ClientGUI()
	{
		btLogin.addActionListener(e -> {
			app.getLoginGUI().getLoginField().setText(null);
			app.getLoginGUI().getPassField().setText(null);
			app.initGui(app.getLoginGUI());
		});

		btSewing.addActionListener(e -> {

		});


		btAddOrder.addActionListener(e -> {
			List<String> services = studio.getAllServicesTypeName();
			DefaultComboBoxModel model = new DefaultComboBoxModel(services.toArray());
			app.getAddOrderGUI().getCbServiceType().setModel(model);
			app.getAddOrderGUI().getCbServiceType().setSelectedIndex(-1);
			app.initGui(app.getAddOrderGUI());
		});
	}


	@Override
	public String getTitle()
	{
		return "Studio";
	}

	@Override
	public JPanel getRootPanel()
	{
		return rootPanel;
	}

}
