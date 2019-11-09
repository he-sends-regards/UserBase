package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    public MainWindow(String fullName, Number money) throws IOException {
        super("Main Window");

        BufferedImage cityImage = ImageIO.read(new File("./src/gui/city-bank300x500.jpg"));
        JLabel cityLabel = new JLabel(new ImageIcon(cityImage));
        BufferedImage avatar = ImageIO.read(new File("./src/gui/myAvatar200x200.png"));
        JLabel avatarLabel = new JLabel(new ImageIcon(avatar));
        JLabel fullNameLabel = new JLabel("Full name: " + fullName);
        JLabel moneyLabel = new JLabel("Money: " + money);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "Send money"));
        buttonPanel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "Get money"));
//        JButton sendBt = new JButton("Send money");
//        JButton getBt = new JButton("Get money");

        Box userBox = Box.createVerticalBox();
        userBox.add(avatarLabel);
        userBox.add(Box.createVerticalStrut(20));
        userBox.add(fullNameLabel);
        userBox.add(Box.createVerticalStrut(20));
        userBox.add(moneyLabel);

        Box cityBox = Box.createHorizontalBox();
        cityBox.add(cityLabel);

        Box workBox = Box.createVerticalBox();
        workBox.add(Box.createVerticalStrut(20));
        workBox.add(cityBox);
        workBox.add(Box.createVerticalStrut(20));
        workBox.add(buttonPanel);
        workBox.add(Box.createVerticalStrut(20));

        Box mainBox = Box.createHorizontalBox();
        mainBox.add(Box.createHorizontalStrut(20));
        mainBox.add(userBox);
        mainBox.add(Box.createHorizontalStrut(20));
        mainBox.add(workBox);
        mainBox.add(Box.createHorizontalStrut(20));

        setContentPane(mainBox);
        pack();
        setResizable(false);
    }

    private JPanel createPanel(Border border, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JButton(text));
        panel.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), border));
        return panel;
    }
}
