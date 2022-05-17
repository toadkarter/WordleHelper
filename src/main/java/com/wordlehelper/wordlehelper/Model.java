package com.wordlehelper.wordlehelper;

public class Model {


    public void getPossibleAnswers(String greenLetters, String yellowLetters, String greyLetters) {
        Guess guess = new Guess(greenLetters, yellowLetters, greyLetters);
        System.out.println(guess);
        System.out.println("To be completed - all possible answers to return to Controller");
    }
}
