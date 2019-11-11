/*
    # Authors: Lutskiy Ivan & Karpenko Danylo
    # Gmail: postscriptum.no@gmail.com
    # Project: BankingSystem
    # Date: the 5th of November 2019
    # GitHub: https://github.com/postscriptumno/BankingSystem
*/
package bin;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;

public class BankingSystem {
    static Stack userData = new Stack();

    public static void main(String[] args) {
        StartWindow myProgram = new StartWindow();
        myProgram.setVisible(true);
        
        // // Vector elements = new Vector();
        // // elements.add(1);
        // // elements.add(2);
        // // elements.add(3);
        // // HistoryWindow historyWindow = new HistoryWindow(elements);
        // // historyWindow.setVisible(true);
    }
}

class StartWindow extends JFrame {
    private static final long serialVersionUID = 1L;

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

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RegisterWindow registerWindow = new RegisterWindow();
                registerWindow.setVisible(true);
            }
        });

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

class RegisterWindow extends JFrame {
    private static final long serialVersionUID = 1L;

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

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fullNameField.getText();
                String login = loginField.getText();
                char[] password = passwordField.getPassword();
                char[] passwordConfirm = passwordConfirmField.getPassword();
                String sex = "";
                String passwordStr = new String(password);
                if (male.isSelected())
                    sex = "Male";
                else if (female.isSelected())
                    sex = "Female";

                if (name.length() != 0 && login.length() != 0 && password.length != 0 && passwordConfirm.length != 0
                        && sex != "") {
                    if (arePasswordsEqual(password, passwordConfirm)) {
                        String userDataStr = ("User's name is: " + name + "\nUser's Login is: " + login
                                + "\nUser's password is: " + passwordStr + "\nSex: " + sex);
                        JOptionPane.showMessageDialog(null, userDataStr);
                        setVisible(false);
                        LoginWindow loginWindow = new LoginWindow();
                        loginWindow.setVisible(true);
                        try {
                            String dataToBase = ("Name: [" + name + "] Login: [" + login + "] Password is: [" + passwordStr + "] Sex: [" + sex + "]\n");
                            FileWriter writer = new FileWriter("base.txt", true);
                            writer.write(dataToBase);
                            writer.flush();
                            writer.close();
                        } catch (Exception exception) {
                            System.err.println("Error with file writing");
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Passwords are not equal");
                } else
                    JOptionPane.showMessageDialog(null, "Empty data");
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                StartWindow myProgram = new StartWindow();
                myProgram.setVisible(true);
            }
        });

        box6.add(Box.createHorizontalGlue());
        box6.add(register);
        box6.add(Box.createHorizontalStrut(12));
        box6.add(cancel);

        fullNameLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());
        loginLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());
        passwordLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
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

    private static boolean arePasswordsEqual(char[] pass1, char[] pass2) {
        boolean isCorrect = true;
        if (pass1.length != pass2.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(pass1, pass2);
        }
        return isCorrect;
    }
}

class LoginWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public LoginWindow() {
        super("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Настраиваем первую горизонтальную панель (для ввода логина)
        Box box1 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(15);

        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(loginField);
        // Настраиваем вторую горизонтальную панель (для ввода пароля)
        Box box2 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(passwordField);
        // Настраиваем третью горизонтальную панель (с кнопками)
        Box box3 = Box.createHorizontalBox();
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("base.txt");
                String login = loginField.getText(); // Введенный логин
                char[] password = passwordField.getPassword(); // Введенный пароль
                String passwordStr = new String(password); // Пароль в строку

                if (login.length() != 0 && password.length != 0) {
                    try {
                        Scanner scanner = new Scanner(file); // Сканер по базе
                        Boolean founder = false; // Искатель совпадения
                        String userLine = new String(); // Найденная строка
                            while (scanner.hasNextLine()) { // Цикл пока есть лайны
                                String line = scanner.nextLine(); // Получает строку
                                if (line.contains("Login: [" + login + "]" + " Password is: [" + passwordStr + "]")){ // Есть ли такие данные в строке
                                    founder = true;
                                    userLine = line;
                                }
                            }

                            if (founder == true) {
                                JOptionPane.showMessageDialog(null, ("User found: " + userLine));
                                setVisible(false);
                                MainWindow mainWindow = new MainWindow(login, 10000);
                                mainWindow.setVisible(true);
                            }
                            else 
                                JOptionPane.showMessageDialog(null, "User not found");
                                scanner.close();
                        } catch (FileNotFoundException exception) {
                            System.err.println("File not found");
                        }
                } else
                    JOptionPane.showMessageDialog(null, "Empty data");
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                StartWindow myProgram = new StartWindow();
                myProgram.setVisible(true);
            }
        });

        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);
        // Уточняем размеры компонентов
        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
        // Размещаем три горизонтальные панели на одной вертикальной
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

class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainWindow(String fullName, Number money) {
        super("Main Window");
        JMenuBar menuBar = new MainWindowMenuBar(MainWindow.this);
        setJMenuBar(menuBar);
        // BufferedImage cityImage = ImageIO.read(new
        // File("../../img/city-bank300x500.jpg"));
        // JLabel cityLabel = new JLabel(new ImageIcon(cityImage));
        // BufferedImage avatar = ImageIO.read(new File("img/myAvatar200x200.png"));
        // JLabel avatarLabel = new JLabel(new ImageIcon(avatar));
        JLabel fullNameLabel = new JLabel("Full name: " + fullName);
        JLabel moneyLabel = new JLabel("Money: " + money);

        // JPanel buttonPanel = new JPanel();
        // buttonPanel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "Send
        // money"));
        // buttonPanel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "Get
        // money"));
        JButton sendBt = new JButton("Send money");
        JButton getBt = new JButton("Get money");

        Box userBox = Box.createVerticalBox();
        // userBox.add(avatarLabel);
        userBox.add(Box.createVerticalStrut(20));
        userBox.add(fullNameLabel);
        userBox.add(Box.createVerticalStrut(20));
        userBox.add(moneyLabel);

        // Box cityBox = Box.createHorizontalBox();
        // cityBox.add(cityLabel);

        Box workBox = Box.createVerticalBox();
        workBox.add(Box.createVerticalStrut(20));
        // workBox.add(cityBox);
        workBox.add(Box.createVerticalStrut(20));

        workBox.add(sendBt);
        workBox.add(getBt);

        workBox.add(Box.createVerticalStrut(20));

        Box mainBox = Box.createHorizontalBox();
        mainBox.add(Box.createHorizontalStrut(20));
        mainBox.add(userBox);
        mainBox.add(Box.createHorizontalStrut(20));
        mainBox.add(workBox);
        mainBox.add(Box.createHorizontalStrut(20));

        sendBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendingMoneyWindow sendMoneyWindow = new SendingMoneyWindow();
                sendMoneyWindow.setVisible(true);
            }
        });

        getBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GettingMoneyWindow getMoneyWindow = new GettingMoneyWindow();
                getMoneyWindow.setVisible(true);
            }
        });

        setContentPane(mainBox);
        pack();
        setResizable(false);
    }

    // private JPanel createPanel(Border border, String text) {
    // JPanel panel = new JPanel();
    // panel.setLayout(new BorderLayout());
    // panel.add(new JButton(text));
    // panel.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), border));
    // return panel;
    // }
}

class GettingMoneyWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public GettingMoneyWindow() {
        super("Getting Money");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Настраиваем первую горизонтальную панель (для ввода логина)
        Box box1 = Box.createHorizontalBox();
        JLabel amountLabel = new JLabel("Amount:");
        JTextField loginField = new JTextField(15);
        box1.add(amountLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(loginField);
        // Настраиваем вторую горизонтальную панель (для ввода пароля)
        Box box2 = Box.createHorizontalBox();
        JLabel idLabel = new JLabel("ID code:");
        JPasswordField idField = new JPasswordField(15);
        box2.add(idLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(idField);
        // Настраиваем третью горизонтальную панель (с кнопками)
        Box box3 = Box.createHorizontalBox();
        JButton ok = new JButton("Get");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);
        // Уточняем размеры компонентов
        idLabel.setPreferredSize(amountLabel.getPreferredSize());
        // Размещаем три горизонтальные панели на одной вертикальной
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

class HistoryWindow extends JFrame {
    private static final long serialVersionUID = 1L;

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

class SendingMoneyWindow extends JFrame {
    private static final long serialVersionUID = 1L;

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
        JTextField amount = new JTextField(15);
        box2.add(money);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(amount);
        // Настраиваем третью горизонтальную панель (с кнопками)
        Box box3 = Box.createHorizontalBox();
        JButton ok = new JButton("Send");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);
        // Уточняем размеры компонентов
        money.setPreferredSize(recLogin.getPreferredSize());
        // Размещаем три горизонтальные панели на одной вертикальной
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

class MainWindowMenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    public MainWindowMenuBar(MainWindow mainWindow) {
        super();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");

        fileMenu.add(exit);

        JMenu userMenu = new JMenu("User");
        JMenuItem logIn = new JMenuItem("Change user/Log in");
        JMenuItem register = new JMenuItem("Register");

        userMenu.add(logIn);
        userMenu.add(register);

        add(fileMenu);
        add(userMenu);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(exit, "Are you sure?") == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginWindow login = new LoginWindow();
                login.setVisible(true);
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegisterWindow register = new RegisterWindow();
                register.setVisible(true);
            }
        });
    }
}