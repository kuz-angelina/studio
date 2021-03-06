package ru.studio.api.model.service;

import java.io.Serializable;

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
	private Integer id;
	private Integer quantity;
	private ServiceType serviceType;
}


