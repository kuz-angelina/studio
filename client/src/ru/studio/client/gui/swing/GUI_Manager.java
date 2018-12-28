package ru.studio.client.gui.swing;

import javax.swing.*;
import java.awt.*;


public class GUI_Manager {
    public static void main(String[] args) {
    JFrame frame = new JFrame("Менеджер");
    JButton button = new JButton("Офромить");
    JButton button1 = new JButton("Офромить");

    JTabbedPane tabbedPane = new JTabbedPane();
    StudioTab tabCheckout = new StudioTab();
    StudioTab tailorData = new StudioTab();
    StudioTab listOrder = new StudioTab();
    JLabel orders = new JLabel("Список заказов: ");

    String[] columnNames = {
            "№",
            "ФИО",
            "Телефон",
            "Что делать",
            "Тип одежды",
            "Сформировать заказ"

    };
    String[][] data = {
                {"1", "Соловьева А.Д.","8-996-345-13-54","Пошив","Юбка", "Button"},
                {"2", "Иванов И.И.","8-923-234-53-21","Ремонт","Куртка", "Button"},
    };
    JTable table = new JTable(data,columnNames);

    frame.setLayout(new BorderLayout());

    tabbedPane.addTab("Оформить заказ", tabCheckout);
    tabbedPane.addTab("Данные о портных", tailorData);
    tabbedPane.addTab("Список заказов",listOrder);

    JPanel panel = new JPanel(new GridLayout(2,1));


    panel.add(orders);
    panel.add(table);

    frame.add(tabbedPane,BorderLayout.NORTH);
    frame.add(panel, BorderLayout.CENTER);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);


}
}
