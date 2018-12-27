package ru.studio.client.gui.swing;

import javax.swing.*;
import java.awt.*;

public class GUI_Tailor{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Портной");
        JLabel listOrder = new JLabel("Список заказов: ");
        JButton information = new JButton("Информация");
        String[] columnNames = {
                "Номер",
                "ФИО",
                "Заказы"
        };
        String[][] data = {
                {"1", "Иванов Иван Иванович", "Button", ""},
                {"2", "Михеенко Алиса Дмитриевна", "Button", ""},
        };
        JTable table = new JTable(data,columnNames);

        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JPanel panel = new JPanel(new GridLayout(1,1));
        panel.setBorder(BorderFactory.createEmptyBorder(10,50,130,10));

        mainPanel.add(listOrder);
        mainPanel.add(table);
        panel.add(information);

        frame.add(mainPanel,BorderLayout.CENTER);
        frame.add(panel, BorderLayout.EAST);

        frame.pack();
        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}

