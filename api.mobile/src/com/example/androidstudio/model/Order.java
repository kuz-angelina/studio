package com.example.androidstudio.model;

import java.io.Serializable;

import com.example.androidstudio.model.clothes.ClotheType;
import com.example.androidstudio.model.footwear.FootwearType;
import com.example.androidstudio.model.role.Client;
import com.example.androidstudio.model.role.Manager;
import com.example.androidstudio.model.role.Tailor;
import com.example.androidstudio.model.service.Service;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelina Kuzmina
 */
@Getter
@Setter
public class Order implements Serializable
{
	private Integer id;
	private Boolean tailorAssignment;
	private Manager manager;
	private Client client;
	private Tailor tailor;
	private Service service;
	private ClotheType clothesType;
	private FootwearType footwearType;
	private Double cost;
	private Boolean complete;
	private Boolean givenOut;
}
