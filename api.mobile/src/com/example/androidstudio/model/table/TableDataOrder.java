

package com.example.androidstudio.model.table;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 * Created on 04.01.2019
 */
@Getter
@Setter
public class TableDataOrder implements Serializable
{
	private Integer id;
	private Boolean tailorAssignment;
	private String clotherType;
	private int clotherTypeId;
	private Integer serviceId;
	private String serviceType;
	private int serviceTypeId;
	private String repairType;
	private int repairTypeId;
	private Integer serviceDateId;
	private String measurements;
	private String modeling;
	private String pattern;
	private String stitching;
	private Boolean complete;
	private Double cost;
	private Boolean givenOut;
	private String clientLogin;
}
