package ru.studio.api.model.role;

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

	public Tailor(Integer id, String login, String password, String name, String phoneNumber, String address, Integer experience, Date dateOfAdoption)
	{
		super(id, login, password, name);
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.experience = experience;
		this.dateOfAdoption = dateOfAdoption;
	}
}

