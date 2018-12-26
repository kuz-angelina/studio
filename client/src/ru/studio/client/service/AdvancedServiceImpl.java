package ru.studio.client.service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;

import ru.studio.api.services.AdvancedService;
import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;



/**
 * @author Angelina Kuzmina
 */
public class AdvancedServiceImpl implements AdvancedService
{
	String url = "http://localhost:8080/advanced";
	HessianProxyFactory factory = new HessianProxyFactory();

	AdvancedService advancedService;

	@Override
	public List<ServiceType> getAllServiceType()
	{
		try
		{
			advancedService = (AdvancedService) factory.create(AdvancedService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return advancedService.getAllServiceType();
	}

	@Override
	public List<RepairType> getAllRepairType()
	{
		ArrayList<RepairType> repairTypes = new ArrayList<>();
		RepairType shorter = new RepairType();
		shorter.setName("Укоротить");

		RepairType coloring = new RepairType();
		coloring.setName("Покрасить");

		repairTypes.add(shorter);
		repairTypes.add(coloring);

		return repairTypes;
	}

	@Override
	public List<ClotheType> getAllClothesType()
	{

		ArrayList<ClotheType> clothesTypes = new ArrayList<>();

		ClotheType rubaha = new ClotheType();
		rubaha.setName("Рубашка");

		ClotheType shorty = new ClotheType();
		shorty.setName("Шорты");

		clothesTypes.add(rubaha);
		clothesTypes.add(shorty);

		return clothesTypes;
	}

	@Override
	public ClotheType getClotheTypeByID(Integer clotheId)
	{
		return null;
	}

	@Override
	public ServiceType getServiceTypeById(Integer serviceTypeId)
	{
		return null;
	}

	@Override
	public RepairType getRepairTypeById(Integer repairTypeId)
	{
		return null;
	}
}
