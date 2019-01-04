/*********************************************************************
 * The Initial Developer of the content of this file is NOVARDIS.
 * All portions of the code written by NOVARDIS are property of
 * NOVARDIS. All Rights Reserved.
 *
 * NOVARDIS
 * Detskaya st. 5A, 199106 
 * Saint Petersburg, Russian Federation 
 * Tel: +7 (812) 331 01 71
 * Fax: +7 (812) 331 01 70
 * www.novardis.com
 *
 * (c) 2018 by NOVARDIS
 *********************************************************************/

package ru.studio.server.dao;

import java.util.List;

import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;

/**
 * @author viacheslav.iakovitskii@novardis.com
 * Created on 12/29/18
 */
public interface AdvancedDao
{
	List<ServiceType> getAllServices();

	List<RepairType> getAllRepairTypes();

	List<ClotheType> getAllClotherTypes();

	ServiceType getServiceTypeByID(Integer id);

	RepairType getRepairTypeById(Integer id);

	ClotheType getClotheTypeById(Integer id);
}


