package com.example.androidstudio.model.service;


import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class ServiceDate implements Serializable
{
	private Integer id;
	private Date measurements;
	private Date modeling;
	private Date pattern;
	private Date stitching;
}
