/**
 * @author Angelina Kuzmina
 * Created on 12/29/18
 */

package ru.studio.server.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ru.studio.api.model.footwear.FootwearType;
import ru.studio.api.services.AdvancedService;
import ru.studio.server.service.AdvancedServiceImpl;


/**
 * @author viacheslav.iakovitskii@novardis.com
 * Created on 4/18/19
 */
public class ConApp
{
	private AdvancedService advancedService = new AdvancedServiceImpl();
	BufferedReader reader = null;

	public static void main(String[] args) throws IOException
	{
		ConApp app = new ConApp();
		System.out.println("Добро пожаловать в консольный модуль для работы с обувью в Ателье");
		app.printFooter();
		app.editorNewFootwear();
	}

	private void editorNewFootwear() throws IOException
	{
		System.out.println("Сделайте выбор");
		System.out.println("1 - Создать новую обувь");
		System.out.println("2 - Изменить существующую обувь");

		reader = new BufferedReader(new InputStreamReader(System.in));

		switch (Integer.valueOf(reader.readLine()))
		{
			case 1:
				newFootWear();
				break;
			case 2:
				editFooterWear();
				break;
			default:
				System.out.println("Введите корректное значение");
				editFooterWear();
		}
	}

	private void newFootWear() throws IOException
	{
		FootwearType footwearType = new FootwearType();
		initFooterWear(footwearType);
		editorNewFootwear();
	}

	private void initFooterWear(FootwearType footwearType) throws IOException
	{
		System.out.println("Введите данные обуви");

		System.out.println("Название: ");
		reader = new BufferedReader(new InputStreamReader(System.in));
		footwearType.setName(reader.readLine());

		System.out.println("Размер: ");
		reader = new BufferedReader(new InputStreamReader(System.in));
		footwearType.setSize(Integer.valueOf(reader.readLine()));

		System.out.println("Цвет: ");
		reader = new BufferedReader(new InputStreamReader(System.in));
		footwearType.setColor(reader.readLine());

		advancedService.savefootwearType(footwearType);
		System.out.println("Изменения сохранены в БД");

		printFooter();
	}

	private void editFooterWear() throws IOException
	{
		System.out.println("Введите id обуви");
		reader = new BufferedReader(new InputStreamReader(System.in));
		FootwearType footwearTypeByID = advancedService.getFootwearTypeByID(Integer.valueOf(reader.readLine()));
		initFooterWear(footwearTypeByID);
		editorNewFootwear();
	}

	private void printFooter()
	{
		List<FootwearType> allFootwearType = advancedService.getAllFootwearType();
		System.out.println("Обувь сохраненная в БД");
		allFootwearType.forEach(i -> {
			System.out.println();
			System.out.println("ID: " + i.getId());
			System.out.println("Название: " + i.getName());
			System.out.println("Размер: " + i.getSize());
			System.out.println("Цвет: " + i.getColor());
		});
		System.out.println();
		System.out.println();
	}
}
