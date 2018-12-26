

package ru.studio.server.service;

import java.util.ArrayList;
import java.util.List;

import com.caucho.hessian.server.HessianServlet;

import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;
import ru.studio.api.services.AdvancedService;


/**
 * @author Angelina Kuzmina
 */

public class AdvancedServiceImpl extends HessianServlet implements AdvancedService
{
	@Override
	public List<ServiceType> getAllServiceType()
	{
		ArrayList<ServiceType> list = new ArrayList<>();

		ServiceType repair = new ServiceType();
		repair.setName("Ремонт");

		ServiceType sewing = new ServiceType();
		sewing.setName("Пошив");

		list.add(repair);
		list.add(sewing);
		return list;
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
//		RepairType

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
