package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class HistoryWindow extends JFrame {
    public HistoryWindow(Vector elements) {
        super("History of operations");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        String text = "<html>The history of your operations: " + "<ul>";
        for (int i = 0; i < elements.size(); i++) {
            text = text + "<li>" + elements.get(i) + "</li>";
        }
        text += "</ul>";

        JLabel label = new JLabel(text);

        final JScrollPane scroll = new JScrollPane(label);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        mainPanel.add(scroll, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
        setPreferredSize(new Dimension(350, 200));
        pack();
        setResizable(false);
    }
}
