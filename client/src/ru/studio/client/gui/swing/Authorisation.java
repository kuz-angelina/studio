package ru.studio.client.gui.swing;

import javax.swing.*;
import java.awt.*;

public class Authorisation {
    public static void main(String[] args) {
        JFrame frame = new JFrame(" Авторизация");
        JLabel label = new JLabel("Введите свои данные: ");
        JLabel login = new JLabel("Логин");
        JTextField log = new JTextField(" ");
        JLabel password = new JLabel("Пароль");
        JPasswordField pass = new JPasswordField();
        JButton button = new JButton("Войти");
        JLabel registration = new JLabel("Регистрация");

        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(1,1));
        JPanel panel = new JPanel(new GridLayout(7,1));
        JPanel buttonPanel = new JPanel(new GridLayout(2,1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,50,0,10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,60,10,60));



        mainPanel.add(label);
        panel.add(login);
        panel.add(log);
        panel.add(password);
        panel.add(pass);
        buttonPanel.add(button);
        buttonPanel.add(registration);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel,BorderLayout.SOUTH);

        frame.setMinimumSize(new Dimension(300, 150));
        frame.setMaximumSize(new Dimension(350, 250));
        log.setSize(new Dimension(100, 10));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

