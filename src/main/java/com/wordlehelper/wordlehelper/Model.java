package com.wordlehelper.wordlehelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Model {
    Dictionary dictionary;
    Guess guess;
    ArrayList<String> answers = new ArrayList<>();

    public Model() throws FileNotFoundException {
        dictionary = new Dictionary();
    }

    public void getPossibleAnswers(String greenLetters, String yellowLetters, String greyLetters) {
        guess = new Guess(greenLetters, yellowLetters, greyLetters);
    }

    private void generatePotentialAnswers(char[] currentGuess, String answer) {
        if (currentGuess.length == 0) {
            checkIfValidAnswer(answer);
        }
    }

    private void checkIfValidAnswer(String answer) {
        if (dictionary.isInDictionary(answer) && hasIncludedLetters(answer)) {
            answers.add(answer);
        }
    }

    private boolean hasIncludedLetters(String answer) {
        for (char includedLetter: guess.getIncludedLetters()) {
            if (!answer.contains(String.valueOf(includedLetter))) {
                return false;
            }
        }
        return true;
    }
}
