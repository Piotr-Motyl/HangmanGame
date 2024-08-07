package com.github.piotrmotyl;

import java.io.IOException;
import java.util.*;

public class Hangman {

    char[] userWord;
    int lives = 7;
    int stepNumber = 1;
    List<Character> userChosenLettersList = new ArrayList<>();

    RandomWordsFromFile generatorFromFile = new RandomWordsFromFile("hangmanEnglishWords.txt");
    String wordFromFile = generatorFromFile.generateWord();

    public void play() {
        Scanner scanner = new Scanner(System.in);

        userWord = new char[wordFromFile.length()];
        Arrays.fill(userWord, '_');

        while (!gameEnded()) {
            System.out.println("***** STEP NUMBER: " + stepNumber + " *****");
            System.out.println(userWord);
            System.out.println();
            System.out.println("Select the letter");
            System.out.println("Your chosen letters: " + userChosenLettersList);
            System.out.println("Remaining lives: " + lives);

            char userLetter = 0;
            try {
                userLetter = scanner.nextLine().charAt(0);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("= ! Line cannot be empty ! =");
            }

            checkLetter(userLetter);
            userChosenLettersList.add(userLetter);
            stepNumber++;
            System.out.println("=====================");
        }

        if (lives == 0) {
            System.out.println("=== GAME OVER ===");
            System.out.println("You lost in " + stepNumber + " steps :(");
            System.out.println("Searched word is: " + wordFromFile);
        } else {
            System.out.println("=== YOU WIN ===");
            System.out.println("You win in " + stepNumber + " steps :)");
            System.out.println("Searched word is: " + wordFromFile);
        }
        scanner.close();    }

    private void checkLetter(char letter) {
        boolean foundLetter = false;

        for (int i = 0; i< wordFromFile.length(); i++) {
            if (wordFromFile.charAt(i) == letter) {
                userWord[i] = letter;
                foundLetter = true;
            }
        }

        if (!foundLetter) {
            System.out.println("No such letter, keep searching the letter");
            lives--;
        }
    }

    private boolean gameEnded() {
        return lives == 0 || wordFromFile.equals(String.valueOf(userWord));
    }

    public Hangman() throws IOException {
    }
}