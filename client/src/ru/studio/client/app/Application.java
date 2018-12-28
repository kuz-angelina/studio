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
	private MainClientGUI mainClientGUI;
	private MainManagerGUI mainManagerGUI;
	private MainTailorGUI mainTailorGUI;

	private Client client;
	private Manager manager;
	private Tailor tailor;

	private Studio studio;
	private JFrame mainGUI;
	private JFrame dialog;

	private OrderService orderService;
	private UserService userService;
	private AdvancedService advancedService;

	public static String GUI_CLIENT = "Клиент";
	public static String GUI_MANAGER = "Менеджер";
	public static String GUI_TAILOR = "Портной";
	public static String GUI_REG = "Регистрация";
	public static String GUI_ADD_ORDER = "Добавить заказ";
	public static String GUI_LOGIN = "Авторизация";


	public final static String SERVICE_TYPE_REPAIR = "Ремонт";
	public final static String SERVICE_TYPE_SEWING = "Пошив";

	private static String ROLE_MANAGER = "Manager";
	private static String ROLE_TAILOR = "Tailor";
	private static String ROLE_CLIENT = "Client";

	public void createObject()
	{
		loginGUI = new LoginGUI(GUI_LOGIN);
		addOrderGUI = new AddOrderGUI(GUI_ADD_ORDER);
		regGUI = new RegGUI(GUI_REG);
		mainClientGUI = new MainClientGUI(GUI_CLIENT);
		mainManagerGUI = new MainManagerGUI(GUI_MANAGER);
		mainTailorGUI = new MainTailorGUI(GUI_TAILOR);

		studio = new Studio("Нитки и Иголки", "СПб, Советская 36", "+79997775555", "10:00 - 22:00");

		orderService = new OrderServiceImpl();
		userService = new UserServiceImpl();
		advancedService = new AdvancedServiceImpl();

		mainGUI = new MainClientGUI(GUI_CLIENT);
		dialog = new JFrame();
	}

	public void injectObject()
	{
		mainClientGUI.setApp(this);
		mainClientGUI.setStudio(studio);

		loginGUI.setApp(this);
		loginGUI.setStudio(studio);

		studio.setOrderServices(orderService);
		studio.setUserService(userService);
		studio.setAdvancedService(advancedService);

		regGUI.setApp(this);
		regGUI.setStudio(studio);

		addOrderGUI.setApp(this);
		addOrderGUI.setStudio(studio);
	}

	//	public void initGui(GUItabble gui)
	//	{
	//		//TODO: add clean method switch
	//		String title = gui.getTitle();
	//		JFrame frame = new JFrame(title);
	//		frame.setContentPane(gui.getRootPanel());
	//		frame.pack();
	//		frame.setLocationRelativeTo(null);
	//		frame.setVisible(true);
	//		isMainGUI(title, frame);
	//	}

	public void initGui(JFrame frame)
	{
		String title = frame.getTitle();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		if (GUI_CLIENT.equals(title) || GUI_MANAGER.equals(title) || GUI_TAILOR.equals(title))
		{
			mainGUI = frame;
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		else
		{
			dialog.setVisible(false);
			dialog = frame;
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
			initGui(mainManagerGUI);
		}
		if (ROLE_TAILOR.equals(role))
		{
			this.mainGUI.setVisible(false);
			tailor = (Tailor) user;
			initGui(mainTailorGUI);
		}
		if (ROLE_CLIENT.equals(role))
		{
			client = (Client) user;
			mainClientGUI.getBtAddOrder().setEnabled(true);
			mainClientGUI.getBtEditOrder().setEnabled(true);
			mainClientGUI.getBtDelOrder().setEnabled(true);
		}
	}

	public User validateAndCreateUser(JFrame gui)
	{
		String title = gui.getTitle();
		User user = null;

		if (GUI_REG.equals(title))
		{
			user = getUserfromRegForm(gui);
		}
		return user;
	}

	private User getUserfromRegForm(JFrame gui)
	{
		Client user = new Client();
		RegGUI regGUI = (RegGUI) gui;

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
}
