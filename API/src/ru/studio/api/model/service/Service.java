package ru.studio.api.model.service;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public abstract class Service implements Serializable
{
	private Long id;
	private Integer quantity;
	private ServiceType serviceType;
}


