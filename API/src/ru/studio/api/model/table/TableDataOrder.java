

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
	int clotherTypeId;
	Integer serviceId;
	String serviceType;
	int serviceTypeId;
	String repairType;
	int repairTypeId;
	Integer serviceDateId;
	String measurements;
	String modeling;
	String pattern;
	String stitching;
	Boolean complete;
	Double cost;
	Boolean givenOut;
}
