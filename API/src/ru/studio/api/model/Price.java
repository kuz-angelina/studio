package ru.studio.api.model;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.service.ServiceType;
import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.service.RepairType;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Price
{
	private ClotheType clothesType;
	private ServiceType serviceType;
	private RepairType repairType;
	private Double cost;
}
