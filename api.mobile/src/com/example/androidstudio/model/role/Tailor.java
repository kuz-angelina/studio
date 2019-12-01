package com.example.androidstudio.model.role;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Tailor extends User
{
	private String phoneNumber;
	private String address;
	private Integer experience;
	private Date dateOfAdoption;

	public Tailor()
	{
	}

	public Tailor(Integer id, String login, String password, String name, String phoneNumber, String address, Integer experience, Date dateOfAdoption, UserType userType)
	{
		super(id, login, password, name, userType);
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.experience = experience;
		this.dateOfAdoption = dateOfAdoption;
	}
}

