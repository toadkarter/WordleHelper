package com.wordlehelper.wordlehelper.model.services;

public class Answer implements Comparable<Answer> {
    private final String word;
    private final Double frequency;

    public Answer(String word, Double frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public Double getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Answer otherAnswer) {
        if (this.getFrequency() < otherAnswer.getFrequency()) {
            return 1;
        }
        return -1;
    }
}
