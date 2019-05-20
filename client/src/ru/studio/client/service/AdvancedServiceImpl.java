package ru.studio.client.service;

import java.net.MalformedURLException;
import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;

import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.footwear.FootwearType;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;
import ru.studio.api.services.AdvancedService;


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
		try
		{
			advancedService = (AdvancedService) factory.create(AdvancedService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return advancedService.getAllRepairType();
	}

	@Override
	public List<ClotheType> getAllClothesType()
	{

		try
		{
			advancedService = (AdvancedService) factory.create(AdvancedService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return advancedService.getAllClothesType();
	}

	@Override
	public List<FootwearType> getAllFootwearType()
	{

		try
		{
			advancedService = (AdvancedService) factory.create(AdvancedService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return advancedService.getAllFootwearType();
	}

	@Override
	public ClotheType getClotheTypeByID(Integer clotheId)
	{
		try
		{
			advancedService = (AdvancedService) factory.create(AdvancedService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return advancedService.getClotheTypeByID(clotheId);
	}

	@Override
	public FootwearType getFootwearTypeByID(Integer clotheId)
	{
		try
		{
			advancedService = (AdvancedService) factory.create(AdvancedService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return advancedService.getFootwearTypeByID(clotheId);
	}

	@Override
	public ServiceType getServiceTypeById(Integer serviceTypeId)
	{
		try
		{
			advancedService = (AdvancedService) factory.create(AdvancedService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return advancedService.getServiceTypeById(serviceTypeId);
	}

	@Override
	public RepairType getRepairTypeById(Integer repairTypeId)
	{
		try
		{
			advancedService = (AdvancedService) factory.create(AdvancedService.class, url);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return advancedService.getRepairTypeById(repairTypeId);
	}

	@Override
	public void savefootwearType(FootwearType footwearType)
	{
		advancedService.savefootwearType(footwearType);
	}

	@Override
	public void updatefootwearType(FootwearType footwearType) {

	}
}
