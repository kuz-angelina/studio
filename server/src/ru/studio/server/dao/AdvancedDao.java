
package ru.studio.server.dao;

import java.util.List;

import com.example.androidstudio.model.clothes.ClotheType;
import com.example.androidstudio.model.footwear.FootwearType;
import com.example.androidstudio.model.service.RepairType;
import com.example.androidstudio.model.service.ServiceType;

/**
 * @author Angelina Kuzmina
 * Created on 12/29/18
 */
public interface AdvancedDao
{
	List<ServiceType> getAllServices();

	List<RepairType> getAllRepairTypes();

	List<ClotheType> getAllClotherTypes();

	List<FootwearType> getAllFootwearTypes();

	ServiceType getServiceTypeByID(Integer id);

	RepairType getRepairTypeById(Integer id);

	ClotheType getClotheTypeById(Integer id);

	FootwearType getFootwearTypeById(Integer id);

	void savefootwearType(FootwearType footwearType);

	void updateFootwearType(FootwearType footwearType);
}


