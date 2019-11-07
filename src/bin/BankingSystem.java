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

        File file = new File("base.txt");
        Scanner scanner = new Scanner(file);

        Boolean founder = false;
        String userLine = new String();
        try {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Name: \'" + nameCheck + "\'" + " Password: " + "\'" + passwordCheck + "\'")) {
                    founder = true;
                    userLine = line;
                }
            }
            if (founder == true) {
                System.out.println("Account has been found");
                System.out.println(userLine);
            } else {
                System.out.println("Wrong name or password");
            }
        } finally {   
            scanner.close();
        }
        userCheck.close();
    }

    // Функция для отправки денег другому пользователю
    private static void send() throws IOException {
        File file = new File("base.txt");

        Scanner moneySendProcess = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String senderName = moneySendProcess.nextLine();

        System.out.println("Enter your password: ");
        String senderPassword = moneySendProcess.nextLine();

        Scanner senderScanner = new Scanner(file);
        Boolean hasUser = false;
        try {
            while(senderScanner.hasNextLine()) {
                String line = senderScanner.nextLine();
                if (line.contains("Name: \'" + senderName + "\'" + " Password: " + "\'" + senderPassword + "\'")) {
                    hasUser = true;
                }
            }
        } finally {   
            System.out.println(hasUser ? "User found" : "User not found");
            senderScanner.close();
        }
        
        Scanner recipientScanner = new Scanner(file);
        System.out.print("To whom money to send: ");
        String recipientName = moneySendProcess.nextLine();
        Boolean hasRecipient = false;
        try {
            while(recipientScanner.hasNextLine()) {
                String line = recipientScanner.nextLine();
                if (line.contains("Name: \'" + recipientName + "\'")) {
                    hasRecipient = true;
                }
            }
        } finally {   
            System.out.println(hasRecipient ? "Recipient found" : "Recipient not found");
            recipientScanner.close();
        }

        System.out.print("How much: ");
        String amount = moneySendProcess.nextLine();

        moneySendProcess.close();
        System.out.println(amount + " (uah) has been sent to " + recipientName);
    }
}