package com.example.androidstudio.model.clothes;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class ClotheType implements Serializable
{
	private Integer id;
	private String name;
}
