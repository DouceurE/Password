package main.java.com.PasswordCracker;
//import main.java.com.PasswordCracker.PasswordCrackerGUI;
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PasswordCrackerGUI().createAndShowGUI();
            }
        });
    }
}
