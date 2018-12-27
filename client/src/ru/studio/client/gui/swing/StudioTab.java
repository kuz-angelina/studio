package ru.studio.client.gui.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author Angelina Kuzmina
 * Created on 05.12.18
 */

public class StudioTab extends JPanel {

    public StudioTab() {
        ListPanel colorListPanel = new ListPanel();
        Panel colorPanel = new Panel();

        // JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, colorListPanel, colorPanel);
        //  splitPane.setDividerLocation(300);

        setLayout(new BorderLayout());
        // add(splitPane);

       /* colorListPanel.addColorSelectionListener(new ColorSelectionListener() {
            public void select(Color color) {
                colorPanel.setColor(color);
            }
        });
*/
    }
}