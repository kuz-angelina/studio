package ru.studio.api.model.service;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class ServiceSewing extends Service
{
	private ServiceDate serviceDate;
}
