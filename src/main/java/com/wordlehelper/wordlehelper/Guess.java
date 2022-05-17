package com.wordlehelper.wordlehelper;

public class Guess {
    private final String correctLetters;
    private final String includedLetters;
    private final String possibleLetters;

    public Guess(String correctLetters, String includedLetters, String wrongLetters) {
        this.correctLetters = correctLetters.toLowerCase();
        this.includedLetters = includedLetters.toLowerCase();
        this.possibleLetters = getPossibleLetters(wrongLetters);
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
        StringBuilder alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        for (char wrongLetter: wrongLetters.toCharArray()) {
            removeWrongLetterFromAlphabet(alphabet, wrongLetter);
        }
        return alphabet.toString();
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
                "correctLetters='" + correctLetters + '\'' +
                ", includedLetters='" + includedLetters + '\'' +
                ", possibleLetters='" + possibleLetters + '\'' +
                '}';
    }
}
