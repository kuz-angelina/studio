package ru.studio.client.gui.swing;

import javax.swing.*;
import java.awt.*;

public class Registration {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Регистрация");
        JLabel registration = new JLabel("Регистрация: ");
        JLabel name = new JLabel("Ваше имя или название организации: ");
        JTextField name1 = new JTextField("");
        JLabel email = new JLabel("Ваш e-mail: ");
        JTextField email1 = new JTextField("");
        JLabel phone = new JLabel("Ваш телефон:");
        JTextField tel = new JTextField("");
        JLabel password = new JLabel("Пароль: ");
        JPasswordField pass = new JPasswordField("");
        JLabel password1 = new JLabel("Пароль: ");
        JPasswordField pass1 = new JPasswordField("");
        JButton button = new JButton("Отправить");

        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(1,1));
        JPanel panel = new JPanel(new GridLayout(6,1));
        JPanel buttonPanel = new JPanel(new GridLayout(1,1));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,400,10,10));

        mainPanel.add(registration);
        panel.add(name);
        panel.add(name1);
        panel.add(email);
        panel.add(email1);
        panel.add(phone);
        panel.add(tel);
        panel.add(password);
        panel.add(pass);
        panel.add(password1);
        panel.add(pass1);
        buttonPanel.add(button);

        frame.add(mainPanel,BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
