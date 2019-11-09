package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RegisterWindow extends JFrame {
    public RegisterWindow() {
        super("Register");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box box1 = Box.createHorizontalBox();
        JLabel fullNameLabel = new JLabel("Full name:");
        JTextField fullNameField = new JTextField(15);
        box1.add(fullNameLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(fullNameField);

        Box box2 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(15);
        box2.add(loginLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(loginField);

        Box box3 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        box3.add(passwordLabel);
        box3.add(Box.createHorizontalStrut(6));
        box3.add(passwordField);

        Box box4 = Box.createHorizontalBox();
        JLabel passwordConfirmLabel = new JLabel("Confirm password:");
        JPasswordField passwordConfirmField = new JPasswordField(15);
        box4.add(passwordConfirmLabel);
        box4.add(Box.createHorizontalStrut(6));
        box4.add(passwordConfirmField);

        Box box5 = Box.createHorizontalBox();
        JLabel sexLabel = new JLabel("Choose sex:");
        JCheckBox male = new JCheckBox("male");
        JCheckBox female = new JCheckBox("female");
        ButtonGroup bg = new ButtonGroup(); // создаем группу взаимного исключения
        bg.add(male);
        bg.add(female);

        box5.add(sexLabel);
        box5.add(Box.createHorizontalStrut(6));
        box5.add(male);
        box5.add(Box.createHorizontalStrut(6));
        box5.add(female);

        Box box6 = Box.createHorizontalBox();
        JButton register = new JButton("Register");
        JButton cancel = new JButton("Cancel");
        box6.add(Box.createHorizontalGlue());
        box6.add(register);
        box6.add(Box.createHorizontalStrut(12));
        box6.add(cancel);

        fullNameLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());
        loginLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());
        passwordLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box4);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box5);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box6);
        setContentPane(mainBox);
        pack();
        setResizable(false);
    }
}
