package ru.studio.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.footwear.FootwearType;
import ru.studio.api.model.role.Client;
import ru.studio.api.model.role.Manager;
import ru.studio.api.model.role.Tailor;
import ru.studio.api.model.service.Service;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Order implements Serializable
{
	private Integer id;
	private Boolean tailorAssignment;
	private Manager manager;
	private Client client;
	private Tailor tailor;
	private Service service;
	private ClotheType clothesType;
	private FootwearType footwearType;
	private Double cost;
	private Boolean complete;
	private Boolean givenOut;
}
