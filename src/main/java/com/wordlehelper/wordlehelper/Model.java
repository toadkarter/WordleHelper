package com.wordlehelper.wordlehelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

// TODO: Refactor the generation of answers into a separate class
public class Model {
    AnswerGenerator answerGenerator;

    public Model() throws FileNotFoundException {
        answerGenerator = new AnswerGenerator();
    }

    public ArrayList<String> getPossibleAnswers(String greenLetters, String yellowLetters, String greyLetters) {
        Guess guess = new Guess(greenLetters, yellowLetters, greyLetters);
        return answerGenerator.getAnswers(guess);
    }

}
