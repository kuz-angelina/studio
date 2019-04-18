package ru.studio.api.services;


import java.util.List;

import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.footwear.FootwearType;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;

/**
 * @author Angelina Kuzmina
 */
public interface AdvancedService
{
	List<ServiceType> getAllServiceType();

	List<RepairType> getAllRepairType();

	List<ClotheType> getAllClothesType();

	List<FootwearType> getAllFootwearType();

	ClotheType getClotheTypeByID(Integer clotheId);

	FootwearType getFootwearTypeByID(Integer clotheId);

	ServiceType getServiceTypeById(Integer serviceTypeId);

	RepairType getRepairTypeById(Integer repairTypeId);

	void savefootwearType(FootwearType footwearType);

	void updatefootwearType(FootwearType footwearType);
}
