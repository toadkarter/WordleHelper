package com.wordlehelper.wordlehelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;

// TODO: Refactor the generation of answers into a separate class
public class Model {
    Dictionary dictionary;
    Guess guess;
    ArrayList<String> answers;

    public Model() throws FileNotFoundException {
        dictionary = new Dictionary();
    }

    public ArrayList<String> getPossibleAnswers(String greenLetters, String yellowLetters, String greyLetters) {
        guess = new Guess(greenLetters, yellowLetters, greyLetters);
        answers = new ArrayList<>();
        String answer = "";
        generatePotentialAnswers(guess.getCorrectLetters(), answer);
        return answers;
    }

    private void generatePotentialAnswers(String currentGuess, String answer) {
        if (currentGuess.length() == 0) {
            checkIfValidAnswer(answer);
            return;
        }

        if (firstLetterIsInPossibleLetters(currentGuess)) {
            generatePotentialAnswers(currentGuess.substring(1), answer + currentGuess.charAt(0));
        } else {
            for (char possibleLetter: guess.getPossibleLetters().toCharArray()) {
                generatePotentialAnswers(currentGuess.substring(1), answer + possibleLetter);
            }
        }
    }

    private void checkIfValidAnswer(String answer) {
        if (dictionary.isInDictionary(answer) && hasIncludedLetters(answer)) {
            answers.add(answer);
        }
    }

    private boolean hasIncludedLetters(String answer) {
        for (char includedLetter: guess.getIncludedLetters().toCharArray()) {
            if (!answer.contains(String.valueOf(includedLetter))) {
                return false;
            }
        }
        return true;
    }

    private boolean firstLetterIsInPossibleLetters(String answer) {
        String firstLetter = String.valueOf(answer.charAt(0));
        String possibleLetters = guess.getPossibleLetters();
        int locationInPossibleLetters = possibleLetters.indexOf(firstLetter);
        return locationInPossibleLetters != -1;
    }
}
