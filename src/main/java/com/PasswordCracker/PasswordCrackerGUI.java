package main.java.com.PasswordCracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordCrackerGUI {
    private JFrame frame;
    private JTextField passwordField;
    private JComboBox<String> crackerTypeBox;
    private JComboBox<String> methodBox;
    private JButton crackButton;
    private JTextArea resultArea;

    public void createAndShowGUI() {
        frame = new JFrame("Password Cracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        frame.setLayout(new GridLayout(6, 1));

        passwordField = new JTextField();
        frame.add(new JLabel("Enter Password Hash:"));
        frame.add(passwordField);

        String[] crackerTypes = { "Local", "Online" };
        crackerTypeBox = new JComboBox<>(crackerTypes);
        frame.add(new JLabel("Select Cracker Type:"));
        frame.add(crackerTypeBox);

        String[] methods = { "Brute Force", "Dictionary" };
        methodBox = new JComboBox<>(methods);
        frame.add(new JLabel("Select Method:"));
        frame.add(methodBox);

        crackButton = new JButton("Crack Password");
        crackButton.addActionListener(new CrackButtonListener());
        frame.add(crackButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        frame.add(new JScrollPane(resultArea));

        frame.setVisible(true);
    }

    private class CrackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hash = passwordField.getText();
            String crackerType = (String) crackerTypeBox.getSelectedItem();
            String method = (String) methodBox.getSelectedItem();

            PasswordCrackerFactory factory = new PasswordCrackerFactory();
            PasswordCracker cracker = factory.getPasswordCracker(crackerType, method);

            String result = cracker.crack(hash);
            resultArea.setText(result);
        }
    }
}
