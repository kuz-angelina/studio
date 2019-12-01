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

package com.example.androidstudio.model;

import java.io.Serializable;
import java.util.Date;

import com.example.androidstudio.model.role.UserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable
{
	private Integer id;
	private String login;
	private String password;
	private String name;
	private UserType userType;
	private String email;
	private String phoneNumber;
	private String address;
	private Integer experience;
	private Date dateOfAdoption;

}
