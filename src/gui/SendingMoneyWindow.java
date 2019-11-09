package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SendingMoneyWindow extends JFrame {
    public SendingMoneyWindow() {
        super("Send Money");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
// Настраиваем первую горизонтальную панель (для ввода логина)
        Box box1 = Box.createHorizontalBox();
        JLabel recLogin = new JLabel("Recipient login:");
        JTextField loginField = new JTextField(15);
        box1.add(recLogin);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(loginField);
// Настраиваем вторую горизонтальную панель (для ввода пароля)
        Box box2 = Box.createHorizontalBox();
        JLabel money = new JLabel("Money:");
        JPasswordField amount = new JPasswordField(15);
        box2.add(money);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(amount);
// Настраиваем третью горизонтальную панель (с кнопками)
        Box box3 = Box.createHorizontalBox();
        JButton ok = new JButton("Send");
        JButton cancel = new JButton("Cancel");
        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);
// Уточняем размеры компонентов
        money.setPreferredSize(recLogin.getPreferredSize());
// Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
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
