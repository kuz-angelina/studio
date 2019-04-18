

package ru.studio.server.service;

import java.util.List;

import com.caucho.hessian.server.HessianServlet;

import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.footwear.FootwearType;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;
import ru.studio.api.services.AdvancedService;
import ru.studio.server.dao.AdvancedDao;
import ru.studio.server.dao.impl.AdvancedDaoImpl;


/**
 * @author Angelina Kuzmina
 */

public class AdvancedServiceImpl extends HessianServlet implements AdvancedService
{
	AdvancedDao advancedDao = new AdvancedDaoImpl();

	@Override
	public List<ServiceType> getAllServiceType()
	{
//		ArrayList<ServiceType> list = new ArrayList<>();
//
//		ServiceType repair = new ServiceType();
//		repair.setName("Ремонт");
//
//		ServiceType sewing = new ServiceType();
//		sewing.setName("Пошив");
//
//		list.add(repair);
//		list.add(sewing);
//		return list;
		return advancedDao.getAllServices();
	}

	@Override
	public List<RepairType> getAllRepairType()
	{
		return advancedDao.getAllRepairTypes();
	}

	@Override
	public List<ClotheType> getAllClothesType()
	{

		return advancedDao.getAllClotherTypes();
	}

	@Override
	public List<FootwearType> getAllFootwearType()
	{

		return advancedDao.getAllFootwearTypes();
	}

	@Override
	public ClotheType getClotheTypeByID(Integer clotheId)
	{
		return advancedDao.getClotheTypeById(clotheId);
	}

	@Override
	public FootwearType getFootwearTypeByID(Integer clotheId)
	{
		return advancedDao.getFootwearTypeById(clotheId);
	}

	@Override
	public ServiceType getServiceTypeById(Integer serviceTypeId)
	{
		return advancedDao.getServiceTypeByID(serviceTypeId);
	}

	@Override
	public RepairType getRepairTypeById(Integer repairTypeId)
	{
		return advancedDao.getRepairTypeById(repairTypeId);
	}

	@Override
	public void savefootwearType(FootwearType footwearType)
	{
		advancedDao.savefootwearType(footwearType);
	}

	@Override
	public void updatefootwearType(FootwearType footwearType)
	{
		advancedDao.updateFootwearType(footwearType);
	}
}
