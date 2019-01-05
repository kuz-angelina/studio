

package ru.studio.api.model.table;

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
	Integer id;
	Boolean tailorAssignment;
	String clotherType;
	Integer serviceId;
	String serviceType;
	String repairType;
	Integer serviceDateId;
	String measurements;
	String modeling;
	String pattern;
	String stitching;
	Boolean complete;
	Double cost;
	Boolean givenOut;
}
