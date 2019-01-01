package ru.studio.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.service.RepairType;
import ru.studio.api.model.service.ServiceType;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Price implements Serializable
{
	private Integer id;
	private ClotheType clothesType;
	private ServiceType serviceType;
	private RepairType repairType;
	private Double cost;
}
