package com.wordlehelper.wordlehelper.model;

import com.wordlehelper.wordlehelper.model.services.AnswerGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// TODO: Refactor the generation of answers into a separate class
public class Model {
    AnswerGenerator answerGenerator;

    public Model() throws IOException {
        answerGenerator = new AnswerGenerator();
    }

    public ArrayList<String> getPossibleAnswers(String greenLetters, String yellowLetters, String greyLetters) {
        Guess guess = new Guess(greenLetters, yellowLetters, greyLetters);
        return answerGenerator.getAnswers(guess);
    }

}
