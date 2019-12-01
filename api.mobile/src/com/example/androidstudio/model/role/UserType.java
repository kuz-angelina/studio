/*********************************************************************
 * The Initial Developer of the content of this file is NOVARDIS.
 * All portions of the code written by NOVARDIS are property of
 * NOVARDIS. All Rights Reserved.
 *
 * NOVARDIS
 * Detskaya st. 5A, 199106 
 * Saint Petersburg, Russian Federation 
 * Tel: +7 (812) 331 01 71
 * Fax: +7 (812) 331 01 70
 * www.novardis.com
 *
 * (c) 2019 by NOVARDIS
 *********************************************************************/

package com.example.androidstudio.model.role;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserType implements Serializable
{
	public static final String CLIENT = "client";
	public static final String MANAGER = "manager";
	public static final String TAILOR = "tailor";

	private Integer id;
	private String name;

	public UserType(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public UserType()
	{
	}
}
