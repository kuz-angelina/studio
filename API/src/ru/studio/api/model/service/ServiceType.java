package ru.studio.api.model.service;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class ServiceType  implements Serializable
{
	private Long id;
	private String name;
}
