package main.java.com.PasswordCracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BruteForceLocalPasswordCracker implements PasswordCracker {

    @Override
    public String crack(String hash) {
        // Définir l'alphabet des caractères
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Définir la longueur maximale du mot de passe (ajuster selon les besoins)
        int maxLength = 8;

        // Initialiser une chaîne de résultat vide
        String result = null;

        // Itérer sur toutes les longueurs de mot de passe possibles
        for (int length = 1; length <= maxLength; length++) {
            // Générer des combinaisons en utilisant une approche plus économe en mémoire
            List<String> combinations = generateCombinationsEfficient(alphabet, length);

            for (String password : combinations) {
                // Hacher le mot de passe généré avec MD5
                String md5Hash = hashFunction(password, "MD5");
                if (md5Hash.equals(hash)) {
                    result = password + " (MD5)"; // Mot de passe trouvé !
                    break; // Sortir de la boucle interne dès qu'une correspondance est trouvée
                }

                // Hacher le mot de passe généré avec SHA-1
                String sha1Hash = hashFunction(password, "SHA-1");
                if (sha1Hash.equals(hash)) {
                    result = password + " (SHA-1)"; // Mot de passe trouvé !
                    break; // Sortir de la boucle interne dès qu'une correspondance est trouvée
                }
            }

            // Arrêter d'itérer sur les longueurs si une correspondance est trouvée
            if (result != null) {
                break;
            }
        }

        // Renvoyer le mot de passe trouvé ou null s'il n'est pas trouvé
        return result;
    }

    // Fonction pour générer toutes les combinaisons de caractères en utilisant un StringBuilder
    private List<String> generateCombinationsEfficient(String alphabet, int length) {
        List<String> combinations = new ArrayList<>();
        StringBuilder currentCombination = new StringBuilder();

        generateCombinationsRecursive(alphabet, length, currentCombination, combinations);

        return combinations;
    }

    // Fonction récursive pour générer des combinaisons de manière efficace
    private void generateCombinationsRecursive(String alphabet, int length, StringBuilder currentCombination, List<String> combinations) {
        if (length == 0) {
            combinations.add(currentCombination.toString());
            return;
        }

        for (char c : alphabet.toCharArray()) {
            currentCombination.append(c);
            generateCombinationsRecursive(alphabet, length - 1, currentCombination, combinations);
            currentCombination.deleteCharAt(currentCombination.length() - 1); // Retour en arrière
        }
    }

    // Fonction pour hacher un mot de passe avec l'algorithme spécifié
    private String hashFunction(String password, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(password.getBytes());
            return new String(hashedBytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Erreur lors du hachage du mot de passe avec l'algorithme : " + algorithm + " : " + e.getMessage());
            return null;
        }
    }
}
