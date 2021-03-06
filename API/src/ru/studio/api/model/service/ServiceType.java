package ru.studio.api.model.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class ServiceType implements Serializable
{
	private Integer id;
	private String name;
}
