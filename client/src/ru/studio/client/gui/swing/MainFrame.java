package ru.studio.client.gui.swing;


import javax.swing.*;
import java.awt.*;

/**
 * @author Angelina Kuzmina
 * Created on 05.12.18
 */

public class MainFrame extends JFrame {

    private JButton studioButton  = new JButton("Ателье");
    private JButton sewingButton  = new JButton("Пошив одежды");
    private JButton repairsButton = new JButton("Ремонт одежды");

    private JTabbedPane tabbedPane   = new JTabbedPane();
    private JPanel panel             = new JPanel(new GridLayout(3, 1));
    private JPanel bpanel            = new JPanel(new GridLayout(1,1));


    private StudioTab tabMain        = new StudioTab();
    private StudioTab tabCompany     = new StudioTab();
    private StudioTab order          = new StudioTab();
    private StudioTab tabVacncy      = new StudioTab();
    private StudioTab tabContacty    = new StudioTab();

    private JButton entrance = new JButton("Вход");


    public MainFrame(String title) throws HeadlessException {
        super(title);

        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        setLayout(new BorderLayout());

        tabbedPane.addTab("Главная", tabMain);
        tabbedPane.addTab("О компании", tabCompany);
        tabbedPane.addTab("Заявки/Заказы",order);
        tabbedPane.addTab("Вакансии", tabVacncy);
        tabbedPane.addTab("Контакты", tabContacty);


        panel.add(studioButton);
        panel.add(sewingButton);
        panel.add(repairsButton);
        bpanel.add(entrance);

        add(tabbedPane, BorderLayout.CENTER);
        add(panel, BorderLayout.WEST);
        add(bpanel, BorderLayout.EAST);
    }
}
