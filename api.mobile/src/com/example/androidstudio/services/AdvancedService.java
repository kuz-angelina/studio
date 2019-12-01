package com.example.androidstudio.services;


import java.util.List;

import com.example.androidstudio.model.clothes.ClotheType;
import com.example.androidstudio.model.footwear.FootwearType;
import com.example.androidstudio.model.service.RepairType;
import com.example.androidstudio.model.service.ServiceType;

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
