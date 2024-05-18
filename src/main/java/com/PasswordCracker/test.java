package main.java.com.PasswordCracker;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class test {
    public static void main(String args[]){
        Scanner s = null;
        try {
            s = new Scanner(new File("dictionnary"));
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    System.out.println(line);
                }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
        }
    }
    
}
