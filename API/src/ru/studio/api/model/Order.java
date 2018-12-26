package ru.studio.api.model;

import lombok.Getter;
import lombok.Setter;
import ru.studio.api.model.clothes.ClotheType;
import ru.studio.api.model.role.Client;
import ru.studio.api.model.role.Manager;
import ru.studio.api.model.role.Tailor;
import ru.studio.api.model.service.Service;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Order
{
	private Long id;
	private Boolean tailorAssignment;
	private Manager manager;
	private Client client;
	private Tailor tailor;
	private Double cost;
	private Service service;
	private ClotheType clothesType;
	private Boolean complete;
	private Boolean givenOut;
}
