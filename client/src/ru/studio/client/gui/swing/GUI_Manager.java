package ru.studio.client.gui.swing;

import javax.swing.*;
import java.awt.*;

public class GUI_Manager {
    public static void main(String[] args) {
    JFrame frame = new JFrame("Менеджер");
    JTabbedPane tabbedPane = new JTabbedPane();

    JLabel listOrder = new JLabel("Список заказов: ");
    JButton information = new JButton("Информация");
    String[] columnNames = {
            "Number",
            "Name",
            "Order"
    };
    String[][] data = {
            {"1", "Иванов Иван Иванович", "Button", ""},
            {"2", "Михеенко Алиса Дмитриевна", "Button", ""},
    };
    JTable table = new JTable(data,columnNames);

        frame.setLayout(new BorderLayout());
    //  tabbedPane.addTab("Оформить заказ", tabMain);
    //tabbedPane.addTab("Данные о портных", tailorData);
    //tabbedPane.addTab("Список заказов", orderList);

    JPanel mainPanel = new JPanel(new GridLayout(2,1));
    JPanel panel = new JPanel(new GridLayout(1,1));

        mainPanel.add(listOrder);
        mainPanel.add(table);
        panel.add(information);

        frame.add(mainPanel,BorderLayout.CENTER);
        frame.add(panel, BorderLayout.EAST);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


}
}
