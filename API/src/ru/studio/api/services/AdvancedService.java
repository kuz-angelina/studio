package ru.studio.api.services;


import java.util.List;

import ru.studio.api.model.clothes.ClotheType;
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

	ClotheType getClotheTypeByID(Integer clotheId);

	ServiceType getServiceTypeById(Integer serviceTypeId);

	RepairType getRepairTypeById(Integer repairTypeId);
}
