import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static javax.imageio.ImageIO.read;

public class UserBase {
    public static void main(String[] args) {
        StartWindow myProgram = new StartWindow();
        myProgram.setVisible(true);
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
        JButton signIn = new JButton("Log in");
        JButton register = new JButton("Register");
        box3.add(Box.createHorizontalGlue());
        box3.add(signIn);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(register);

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
    String avatarFilePath = "";
    public RegisterWindow() {
        super("Register");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box avatarBox = Box.createHorizontalBox();
        JLabel avatarLabel = new JLabel("Avatar:");
        JButton chooseButton = new JButton("Choose file");

        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    avatarFilePath = file.getPath();
                }
            }
        });
        avatarBox.add(avatarLabel);
        avatarBox.add(Box.createHorizontalStrut(6));
        avatarBox.add(chooseButton);
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
        JLabel sexLabel = new JLabel("Choose sex:");
        JCheckBox male = new JCheckBox("male");
        JCheckBox female = new JCheckBox("female");
        ButtonGroup bg = new ButtonGroup(); // создаем группу взаимного исключения
        bg.add(male);
        bg.add(female);

        box3.add(sexLabel);
        box3.add(Box.createHorizontalStrut(6));
        box3.add(male);
        box3.add(Box.createHorizontalStrut(6));
        box3.add(female);

        Box box4 = Box.createHorizontalBox();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(3);
        box4.add(ageLabel);
        box4.add(Box.createHorizontalStrut(6));
        box4.add(ageField);

        Box box5 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        box5.add(passwordLabel);
        box5.add(Box.createHorizontalStrut(6));
        box5.add(passwordField);

        Box box6 = Box.createHorizontalBox();
        JLabel passwordConfirmLabel = new JLabel("Confirm password:");
        JPasswordField passwordConfirmField = new JPasswordField(15);
        box6.add(passwordConfirmLabel);
        box6.add(Box.createHorizontalStrut(6));
        box6.add(passwordConfirmField);

        Box box7 = Box.createHorizontalBox();
        JButton register = new JButton("Register");
        JButton cancel = new JButton("Cancel");

        box7.add(Box.createHorizontalGlue());
        box7.add(register);
        box7.add(Box.createHorizontalStrut(12));
        box7.add(cancel);

        fullNameLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());
        loginLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());
        passwordLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());
        ageLabel.setPreferredSize(passwordConfirmLabel.getPreferredSize());

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box4);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box5);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box6);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(avatarBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box7);


        setContentPane(mainBox);
        pack();
        setResizable(false);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fullNameField.getText();
                String login = loginField.getText();

                String sex = "";
                if (male.isSelected())
                    sex = "Male";
                else if (female.isSelected())
                    sex = "Female";

                String age = ageField.getText();

                char[] password = passwordField.getPassword();
                char[] passwordConfirm = passwordConfirmField.getPassword();
                String passwordStr = new String(password);

                if (name.length() != 0 && login.length() != 0 && password.length != 0 && passwordConfirm.length != 0
                        && sex != "") {
                    if (arePasswordsEqual(password, passwordConfirm)) {
                        String userDataStr = ("Name: [" + name + "] Login: [" + login + "] Sex: [" + sex + "] Age: ["
                                + age + "] Password is: [" + passwordStr + "] AvatarPath: [" + avatarFilePath + "] \n");
                        JOptionPane.showMessageDialog(null, userDataStr);

                        dispose();
                        LoginWindow loginWindow = new LoginWindow();
                        loginWindow.setVisible(true);

                        try {
                            String dataToBase = ("Name: [" + name + "] Login: [" + login + "] Sex: [" + sex + "] Age: ["
                                    + age + "] Password is: [" + passwordStr + "] AvatarPath: [" + avatarFilePath + "]&end \n");
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
                dispose();
                StartWindow myProgram = new StartWindow();
                myProgram.setVisible(true);
            }
        });
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
                String login = loginField.getText();
                char[] password = passwordField.getPassword();
                String passwordStr = new String(password);

                String userData = "";

                if (login.length() != 0 && password.length != 0) {
                    try {
                        Scanner scanner = new Scanner(file);
                        Boolean founder = false;
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.contains("Login: [" + login + "]")
                                    && line.contains(" Password is: [" + passwordStr + "]")) {
                                founder = true;
                                userData = line;
                            }
                        }

                        if (founder == true) {
                            JOptionPane.showMessageDialog(null, ("User found"));
                            dispose();
                            String name = userData.substring(userData.indexOf("Name: [") + 7,
                                    userData.indexOf("] Login"));
                            String age = userData.substring(userData.indexOf(" Age: [") + 7,
                                    userData.indexOf("] Password"));
                            String sex = userData.substring(userData.indexOf("Sex: [") + 6, userData.indexOf("] Age"));
                            String avatarPath = userData.substring(userData.indexOf("AvatarPath: [") + 13, userData.indexOf("]&end"));
                            // тут наужно брать с базы путь к аватару
                            System.out.println(avatarPath);
                            MainWindow mainWindow = new MainWindow(name, login, age, sex, avatarPath);
                            mainWindow.setVisible(true);
                        } else
                            JOptionPane.showMessageDialog(null, "User not found");
                        scanner.close();
                    } catch (FileNotFoundException exception) {
                        System.err.println("File not found");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Empty data");
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
    private BufferedImage image;
    public MainWindow(String name, String login, String age, String sex, String avatarPath) throws IOException {
        super("Main Window");
        JMenuBar menuBar = new MainWindowMenuBar(MainWindow.this);
        setJMenuBar(menuBar);

        JLabel fullNameLabel = new JLabel("Name: " + name);
        JLabel loginLabel = new JLabel("Login: " + login);
        JLabel ageLabel = new JLabel("Age: " + age);
        JLabel sexLabel = new JLabel("Sex: " + sex);
        BufferedImage avatar;
        if (avatarPath.length() == 0) {
            avatar = read(new File("images/unknown.jpg"));
        } else {
            avatar = read(new File(avatarPath));
        }

        JLabel avatarLabel = new JLabel(new ImageIcon(avatar));

        Box userBox = Box.createVerticalBox();
        userBox.add(Box.createVerticalStrut(20));
        userBox.add(avatarLabel);
        userBox.add(fullNameLabel);
        userBox.add(loginLabel);
        userBox.add(ageLabel);
        userBox.add(sexLabel);

        BufferedImage cityImage = read(new
                File("images/city-bank300x500.jpg"));
        JLabel cityLabel = new JLabel(new ImageIcon(cityImage));
        Box cityBox = Box.createHorizontalBox();
        cityBox.add(cityLabel);

        Box workBox = Box.createVerticalBox();
        workBox.add(Box.createVerticalStrut(20));
        workBox.add(cityBox);
        workBox.add(Box.createVerticalStrut(20));

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
                mainWindow.dispose();
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegisterWindow register = new RegisterWindow();
                register.setVisible(true);
                mainWindow.dispose();
            }
        });
    }
}