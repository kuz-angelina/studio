package com.example.androidstudio.model.role;

import com.example.androidstudio.model.UserDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Client extends User
{
	private String email;
	private String phoneNumber;

	public Client(UserDto userDto) {
		super(userDto.getId(), userDto.getLogin(), userDto.getPassword(), userDto.getName(), userDto.getUserType());
		this.email = userDto.getEmail();
		this.phoneNumber = userDto.getPhoneNumber();
	}

	public Client() {
	}

}
