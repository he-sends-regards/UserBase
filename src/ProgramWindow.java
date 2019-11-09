package gui;

import javax.swing.*;
import java.io.IOException;
import java.util.Vector;

public class ProgramWindow {
    public static void main(String[] args) throws IOException {
        JFrame myProgram = new StartWindow();
        myProgram.setVisible(true);

        JFrame loginWindow = new LoginWindow();
        loginWindow.setVisible(true);

        JFrame registerWindow = new RegisterWindow();
        registerWindow.setVisible(true);

        JFrame sendWindow = new SendingMoneyWindow();
        sendWindow.setVisible(true);

        JFrame getWindow = new GettingMoneyWindow();
        getWindow.setVisible(true);

        Vector elements = new Vector();
        elements.add(1);
        elements.add(2);
        elements.add(3);
        JFrame historyWindow = new HistoryWindow(elements);
        historyWindow.setVisible(true);
        JFrame mainWindow = new MainWindow("Danylo Karpenko", 90111);
        mainWindow.setVisible(true);
    }
}
