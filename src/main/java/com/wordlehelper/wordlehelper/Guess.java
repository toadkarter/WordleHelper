package com.wordlehelper.wordlehelper;

import java.util.Arrays;

public class Guess {
    private final char[] correctLetters;
    private final char[] includedLetters;
    private final char[] possibleLetters;

    public Guess(String correctLetters, String includedLetters, String wrongLetters) {
        this.correctLetters = correctLetters.toLowerCase().toCharArray();
        this.includedLetters = includedLetters.toLowerCase().toCharArray();
        this.possibleLetters = getPossibleLetters(wrongLetters);
    }

    public char[] getCorrectLetters() {
        return correctLetters;
    }

    public char[] getIncludedLetters() {
        return includedLetters;
    }

    public char[] getPossibleLetters() {
        return possibleLetters;
    }

    private char[] getPossibleLetters(String wrongLetters) {
        StringBuilder alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        for (char wrongLetter: wrongLetters.toCharArray()) {
            removeWrongLetterFromAlphabet(alphabet, wrongLetter);
        }
        return alphabet.toString().toCharArray();
    }

    private void removeWrongLetterFromAlphabet(StringBuilder alphabet, char wrongLetter) {
        int locationOfWrongLetter = getLocationOfWrongLetter(alphabet, wrongLetter);
        if (locationOfWrongLetter != -1) {
            alphabet.deleteCharAt(locationOfWrongLetter);
        }
    }

    private int getLocationOfWrongLetter(StringBuilder alphabet, char wrongLetter) {
        return alphabet.indexOf(String.valueOf(wrongLetter));
    }

    @Override
    public String toString() {
        return "Guess{" +
                "correctLetters=" + Arrays.toString(correctLetters) +
                ", includedLetters=" + Arrays.toString(includedLetters) +
                ", possibleLetters=" + Arrays.toString(possibleLetters) +
                '}';
    }
}
