package ru.studio.api.model.service;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public abstract class Service
{
	private Long id;
	private Integer quantity;
	private ServiceType serviceType;
}


