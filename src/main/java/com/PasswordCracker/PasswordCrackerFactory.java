package main.java.com.PasswordCracker;

public class PasswordCrackerFactory {
    public PasswordCracker getPasswordCracker(String type, String method) {
        if ("Local".equalsIgnoreCase(type)) {
            if ("Brute Force".equalsIgnoreCase(method)) {
                return (PasswordCracker) new BruteForceLocalPasswordCracker();
            } else if ("Dictionary".equalsIgnoreCase(method)) {
                return (PasswordCracker) new DictionaryLocalPasswordCracker();
            }
        } else if ("Online".equalsIgnoreCase(type)) {
            // Implémentez une structure similaire pour les crackers en ligne si nécessaire
        }
        return null;
    }
}
