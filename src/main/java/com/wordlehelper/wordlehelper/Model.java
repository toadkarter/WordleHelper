package com.wordlehelper.wordlehelper;

import java.io.FileNotFoundException;

public class Model {

    public void getPossibleAnswers(String greenLetters, String yellowLetters, String greyLetters) throws FileNotFoundException {
        Guess guess = new Guess(greenLetters, yellowLetters, greyLetters);
        Dictionary dictionary = new Dictionary();
        dictionary.readFile();
        System.out.println(guess);
        System.out.println("To be completed - all possible answers to return to Controller");
    }
}
