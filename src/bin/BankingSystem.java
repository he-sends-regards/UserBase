package bin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) throws IOException {
        startCommand();
    }

    private static void startCommand() throws IOException {
        Scanner check = new Scanner(System.in);
        System.out.print("What you need for: ");
        System.out.println("\n(\'register\' - to create an account, \n\'acc\' - to search for your account, \n\'send\' - to send money to another user)");
        String checked = check.next();
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
        check.close();
    }

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
            System.out.println("Your account: [ " + userData + " ]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private static boolean fileContainsWord(String word) throws IOException {
        return new String(Files.readAllBytes(Paths.get("base.txt"))).contains(word);
    }

    private static void send() {
        Scanner moneySend = new Scanner(System.in);
        System.out.print("To whom money to send: ");
        String recipient = moneySend.nextLine();
        System.out.print("How much: ");
        String amount = moneySend.nextLine();

        System.out.println(amount + " (uah) has been sent to " + recipient);
    }
}
