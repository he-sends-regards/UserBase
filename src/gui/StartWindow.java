package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StartWindow extends JFrame {
    public StartWindow() {
        super("Welcome!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box box1 = Box.createHorizontalBox();
        JLabel hello = new JLabel("Hello!");
        hello.setAlignmentX(CENTER_ALIGNMENT);
        box1.add(hello);

        Box box2 = Box.createHorizontalBox();
        JLabel action = new JLabel("Please, log in.");
        action.setAlignmentX(CENTER_ALIGNMENT);
        box2.add(action);

        Box box3 = Box.createHorizontalBox();
        JButton signIn = new JButton("Sign in");
        JButton register = new JButton("Register");
        box3.add(Box.createHorizontalGlue());
        box3.add(signIn);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(register);

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box3);
        setContentPane(mainBox);
        pack();
        setResizable(false);
    }
}
