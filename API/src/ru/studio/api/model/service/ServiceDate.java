package ru.studio.api.model.service;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Angelina Kuzmina
 */
public class ServiceDate  implements Serializable
{
	private Date measurements;
	private Date modeling;
	private Date pattern;
	private Date stitching;
	private Date complete;
}
