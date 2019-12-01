/**
 * @author Angelina Kuzmina
 * Created on 12/29/18
 */

package com.example.androidstudio.model.footwear;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author viacheslav.iakovitskii@novardis.com
 * Created on 4/18/19
 */
@Getter
@Setter
public class FootwearType implements Serializable
{
	private Integer id;
	private String name;
	private Integer size;
	private String color;
}
