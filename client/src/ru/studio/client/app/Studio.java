package ru.studio.client.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.Order;
import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.role.User;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;
import ru.studio.api.services.AdvancedService;
import ru.studio.api.services.OrderService;
import ru.studio.api.services.UserService;


/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Studio
{
	private OrderService orderServices;
	private UserService userService;
	private AdvancedService advancedService;
	private String orgName;
	private String address;
	private String phoneNumber;
	private String workClock;

	public Studio(String orgName, String address, String phoneNumber, String workClock)
	{
		this.orgName = orgName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.workClock = workClock;
	}

	//create order procedure
	void createOrder()
	{
		Order order = orderServices.createOrder();
		System.out.println("Order with id " + order.getId() + " create");
	}

	//get all orders
	List<Order> getOrders()
	{
		return orderServices.getAllOrder();
	}

	//edit order procedure
	void editOrder(Integer id)
	{
		Order order = orderServices.getOrderById(id);
		//modify fields
		orderServices.saveOrder(order);
	}

	//get orders by client
	List<Order> getOrdersByUserId(User user)
	{
		return orderServices.getOrdersByUserId(user.getId());
	}

	public boolean createTicket(JFrame gui)
	{
		return false;
	}

	public User login(String login, String pass)
	{
		User user = userService.getUserByLogin(login);
		if (user != null && user.getLogin() != null && user.getPassword().equals(pass))
		{
			return user;
		}
		else return null;
	}

	public boolean saveUser(User user)
	{
		return userService.saveUser(user);
	}

	public List<RepairType> getAllServiceRepairType()
	{
		return advancedService.getAllRepairType();
	}

	public List<String> getAllServiceRepairTypeName()
	{

		List<RepairType> services = getAllServiceRepairType();
		List<String> servicesName = new ArrayList<>();

		for (RepairType service : services)
		{
			servicesName.add(service.getName());
		}
		return servicesName;
	}

	public List<ServiceType> getAllServicesType()
	{
		return advancedService.getAllServiceType();
	}


	public List<String> getAllServicesTypeName()
	{
		List<ServiceType> services = getAllServicesType();
		List<String> servicesName = new ArrayList<>();

		for (ServiceType service : services)
		{
			servicesName.add(service.getName());
		}
		return servicesName;
	}

	public List<ClotheType> getAllClothesType()
	{
		return advancedService.getAllClothesType();
	}

	public List<String> getAllClothesTypeName()
	{
		List<ClotheType> services = getAllClothesType();
		List<String> servicesName = new ArrayList<>();

		for (ClotheType service : services)
		{
			servicesName.add(service.getName());
		}
		return servicesName;
	}

	public ClotheType getClotheTypeById(Integer clotheId)
	{
		return advancedService.getClotheTypeByID(clotheId);
	}

	public ServiceType getServiceTypeByID(Integer serviceTypeId)
	{
		return advancedService.getServiceTypeById(serviceTypeId);
	}

	public RepairType getRepairTypeById(Integer repairTypeId)
	{
		return advancedService.getRepairTypeById(repairTypeId);
	}

	public void saveOrder(Order order)
	{
		orderServices.saveOrder(order);
	}
}
