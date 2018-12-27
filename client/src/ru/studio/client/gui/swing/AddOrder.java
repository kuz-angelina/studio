package ru.studio.client.gui.swing;

import javax.swing.*;
import java.awt.*;

public class AddOrder {
    public static void main(String[] args) {
    JFrame frame = new JFrame("Добавить заказ");
    JLabel service = new JLabel("Услуга");
    String[] option1 = { "Пошив","Ремонт" };
    JComboBox box1 = new JComboBox(option1);
    JLabel clothingType = new JLabel("Тип одежды");
    String[] option2 = { "Куртка","Джинсы","Рубашка"};
    JComboBox box2 = new JComboBox(option2);
    JLabel typeOfRepair = new JLabel("Тип ремонта");
    String[] option3 = { "Укоротить","Зашить","Поменять молнию","Приделать пугавицу" };
    JComboBox box3 = new JComboBox(option3);
    JButton createOrder = new JButton("Создать заказ");

    frame.setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel(new GridLayout(3,2));
    JPanel buttonPanel = new JPanel(new GridLayout(1,1));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,400,10,10));

        mainPanel.add(service);
        mainPanel.add(box1);
        mainPanel.add(clothingType);
        mainPanel.add(box2);
        mainPanel.add(typeOfRepair);
        mainPanel.add(box3);
        buttonPanel.add(createOrder);

        frame.add(mainPanel,BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


}

}
