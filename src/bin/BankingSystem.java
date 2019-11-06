package bin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class BankingSystem {
    public static void main(String[] args) throws IOException { // Main
        startCommand();
        BankingFrame frame = new BankingFrame();
        frame.setVisible(true);
    }

    private static void startCommand() throws IOException {
        // Считывание ввода из консоли
        Scanner check = new Scanner(System.in);
        System.out.print("What you need for: ");
        System.out.println("\n(\'register\' - to create an account, \n\'acc\' - to search for your account, \n\'send\' - to send money to another user)");
        String checked = check.next();
        // Проверка что хотел пользователь и заупск соответсвующей функции
        switch (checked) {
            case "register":
                register();
                break;
            case "acc":
                signIn();
                break;
            case "send":
                send();
                break;
            default:
                System.out.println("No such services");
                break;
        }
        check.close(); // Закрытие считывателя из консоли
    }

    // Функция регистрации (добавляет пользователя в базу)
    private static void register() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input name: ");
        String user = input.nextLine();
        System.out.print("Input password: ");
        String password = input.nextLine();
        input.close();
        double amount = 100;
        try {
            String userData = ("Name: \'" + user + "\'" + " Password: \'" + password + "\'" + " In your account: " + amount + " (uah)");
            FileWriter writer = new FileWriter("base.txt", true);
            writer.write(userData);
            writer.write("\n");
            writer.flush();
            writer.close();
            System.out.println("Your account: [ " + userData + " ]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Функция для просмотра своего аккаунта
    private static void signIn() throws IOException {
        Scanner userCheck = new Scanner(System.in);
        System.out.print("Your name: ");
        String nameCheck = userCheck.nextLine();

        System.out.print("Your password: ");
        String passwordCheck = userCheck.nextLine();

        if (fileContainsWord(nameCheck) && fileContainsWord(passwordCheck)) System.out.println("Account has been found");
        else System.out.println("Wrong name or password");

        userCheck.close();
    }

    // Функция для парсинга файла для поиска слова
    private static boolean fileContainsWord(String word) throws IOException {
        return new String(Files.readAllBytes(Paths.get("base.txt"))).contains(word);
    }

    // Функция для отправки денег другому пользователю
    private static void send() {
        Scanner moneySend = new Scanner(System.in);
        System.out.print("To whom money to send: ");
        String recipient = moneySend.nextLine();
        System.out.print("How much: ");
        String amount = moneySend.nextLine();
        moneySend.close();
        System.out.println(amount + " (uah) has been sent to " + recipient);
    }
}

class BankingFrame extends JFrame {
    private static final long serialVersionUID = 7526472295622776147L; // Написал об этом в стейте
    BankingFrame() {
        setBounds(500,200,500,500);
        setTitle("Lab #3. Task #1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        BankingPanel panel = new BankingPanel();
        Container pane = getContentPane();
        pane.add(panel);
    }
}

class BankingPanel extends JPanel {
    private static final long serialVersionUID = 7526472295622776147L; // Написал об этом в стейте
    public void paintComponent(Graphics g) {
        setBackground(Color.BLACK);
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.drawString("Hello freaky bitches", 160,40);
    }
}