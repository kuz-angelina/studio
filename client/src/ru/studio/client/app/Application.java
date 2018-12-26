package ru.studio.client.app;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.role.Client;
import ru.studio.api.model.role.Manager;
import ru.studio.api.model.role.Tailor;
import ru.studio.api.model.role.User;
import ru.studio.api.services.AdvancedService;
import ru.studio.api.services.OrderService;
import ru.studio.api.services.UserService;
import ru.studio.client.gui.*;
import ru.studio.client.service.AdvancedServiceImpl;
import ru.studio.client.service.OrderServiceImpl;
import ru.studio.client.service.UserServiceImpl;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Application
{

	private LoginGUI loginGUI;
	private AddOrderGUI addOrderGUI;

	private RegGUI regGUI;
	private ClientGUI clientGUI;
	private ManagerGUI managerGUI;
	private TailorGUI tailorGUI;

	private Client client;
	private Manager manager;
	private Tailor tailor;

	private Studio studio;
	private JFrame mainGUI;
	private JFrame dialog;

	private OrderService orderService;
	private UserService userService;
	private AdvancedService advancedService;

	public static String GUI_CLIENT = "Studio";
	public static String GUI_MANAGER = "Studio manager";
	public static String GUI_TAILOR = "Studio tailor";
	public static String GUI_REG = "Register";
	public static String GUI_ADD_ORDER = "Add order";

	public final static String SERVICE_TYPE_REPAIR = "Ремонт";
	public final static String SERVICE_TYPE_SEWING = "Пошив";

	private static String ROLE_MANAGER = "Manager";
	private static String ROLE_TAILOR = "Tailor";
	private static String ROLE_CLIENT = "Client";

	public void createObject()
	{
		loginGUI = new LoginGUI();
		addOrderGUI = new AddOrderGUI();
		regGUI = new RegGUI();
		clientGUI = new ClientGUI();

		studio = new Studio();

		orderService = new OrderServiceImpl();
		userService = new UserServiceImpl();
		advancedService = new AdvancedServiceImpl();

		dialog = new JFrame();
	}

	public void injectObject()
	{
		clientGUI.setApp(this);
		clientGUI.setStudio(studio);

		loginGUI.setApplication(this);
		loginGUI.setStudio(studio);

		studio.setOrderServices(orderService);
		studio.setUserService(userService);
		studio.setAdvancedService(advancedService);

		regGUI.setApplication(this);
		regGUI.setStudio(studio);

		addOrderGUI.setApp(this);
		addOrderGUI.setStudio(studio);
	}

	public void initGui(GUItabble gui)
	{
		//TODO: add clean method switch
		String title = gui.getTitle();
		JFrame frame = new JFrame(title);
		frame.setContentPane(gui.getRootPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		isMainGUI(title, frame);
	}

	private void isMainGUI(String title, JFrame frame)
	{
		if (GUI_CLIENT.equals(title) || GUI_MANAGER.equals(title) || GUI_TAILOR.equals(title))
		{
			this.mainGUI = frame;
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		else
		{
			dialog.setVisible(false);
			this.dialog = frame;
			dialog.setVisible(true);
			frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
	}

	public void checkRole(User user)
	{
		dialog.setVisible(false);
		String role = user.getClass().getSimpleName();

		if (ROLE_MANAGER.equals(role))
		{
			this.mainGUI.setVisible(false);
			manager = (Manager) user;
			initGui(managerGUI);
		}
		if (ROLE_TAILOR.equals(role))
		{
			this.mainGUI.setVisible(false);
			tailor = (Tailor) user;
			initGui(tailorGUI);
		}
		if (ROLE_CLIENT.equals(role))
		{
			client = (Client) user;
			clientGUI.getBtAddOrder().setEnabled(true);
			clientGUI.getBtEditTIcket().setEnabled(true);
			clientGUI.getBtDelTicket().setEnabled(true);
			clientGUI.getBtAddOrder().setEnabled(true);
		}
	}

	public User validateAndCreateUser(GUItabble gui)
	{
		String title = gui.getTitle();
		User user = null;

		if (GUI_REG.equals(title))
		{
			user = getUserfromRegForm((RegGUI) gui);
		}
		return user;
	}

	private User getUserfromRegForm(RegGUI gui)
	{

		Client user = new Client();
		RegGUI regGUI = gui;

		if (regGUI.getLoginField().getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Логин не может быть пустым");
			return null;
		}
		else
		{
			user.setLogin(regGUI.getLoginField().getText());
		}

		if (regGUI.getNameField().getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Имя не может быть пустым");
			return null;
		}
		else
		{
			user.setName(regGUI.getNameField().getText());
		}
		if (regGUI.getPassField().getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Пароль не может быть пустым");
			return null;
		}
		else
		{
			user.setPassword(regGUI.getPassField().getText());
		}
		if (regGUI.getRePassField().getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Повтор пароля не может быть пустым");
			return null;
		}
		if (!regGUI.getPassField().getText().equals(regGUI.getRePassField().getText()))
		{
			JOptionPane.showMessageDialog(null, "Пароли не совпадают");
			return null;
		}
		user.setEmail(regGUI.getEmailField().getText());
		user.setPhoneNumber(regGUI.getEmailField().getText());
		return user;
	}

	//	public void initComboBox(Enum[] e, JComboBox jComboBox)
	//	{
	//		Enum[] arrSt = null;
	//
	//		System.out.println(e.getClass().getSimpleName());
	//
	//		if (e.getClass().getSimpleName().equals("RepairType[]")) {
	//			arrSt = RepairType.values();
	//		}
	//
	//		if (e.getClass().getSimpleName().equals("ServiceType[]")) {
	//			arrSt = ServiceType.values();
	//		}
	//
	//		String[] arrStr = new String[arrSt.length];
	//				for (int i = 0; i < arrStr.length; i++)
	//		{
	//			arrStr[i] = arrSt[i].toString();
	//		}
	//		jComboBox.setModel(new DefaultComboBoxModel(arrStr));
	//	}
}
