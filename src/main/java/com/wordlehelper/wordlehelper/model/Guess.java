package com.wordlehelper.wordlehelper.model;


public class Guess {
    private final String correctLetters;
    private final String includedLetters;
    private final String possibleLetters;
    private final StringBuilder alphabet;

    public Guess(String correctLetters, String includedLetters, String wrongLetters) {
        alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        this.correctLetters = correctLetters.toLowerCase();
        this.includedLetters = includedLetters.toLowerCase();
        this.possibleLetters = getPossibleLetters(wrongLetters.toLowerCase());
    }

    public String getCorrectLetters() {
        return correctLetters;
    }

    public String getIncludedLetters() {
        return includedLetters;
    }

    public String getPossibleLetters() {
        return possibleLetters;
    }

    private String getPossibleLetters(String wrongLetters) {
        for (char wrongLetter: wrongLetters.toCharArray()) {
            removeWrongLetterFromAlphabet(wrongLetter);
        }
        return alphabet.toString();
    }

    private void removeWrongLetterFromAlphabet(char wrongLetter) {
        int locationOfWrongLetter =  alphabet.indexOf(String.valueOf(wrongLetter));
        if (locationOfWrongLetter != -1) {
            alphabet.deleteCharAt(locationOfWrongLetter);
        }
    }

    @Override
    public String toString() {
        return "Guess{" +
                "correctLetters='" + correctLetters + '\'' +
                ", includedLetters='" + includedLetters + '\'' +
                ", possibleLetters='" + possibleLetters + '\'' +
                '}';
    }
}
