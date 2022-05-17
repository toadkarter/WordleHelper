package com.wordlehelper.wordlehelper;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

public class Controller {

    @FXML
    private HBox correctLettersContainer;

    @FXML
    private TextField includedLettersContainer;

    @FXML
    private TextField wrongLettersContainer;

    private Model model;

    public void initialize() {
        try {
            model = new Model();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void submit() {
        String greenLetters = getGreenLetters();
        String yellowLetters = includedLettersContainer.getText();
        String greyLetters = wrongLettersContainer.getText();

        model.getPossibleAnswers(greenLetters, yellowLetters, greyLetters);
    }

    private String getGreenLetters() {
        ObservableList<Node> greenLetters = correctLettersContainer.getChildren();
        StringBuilder correctLetters = new StringBuilder();
        for (Node letter: greenLetters) {
            addGreenLetter(correctLetters, (TextField) letter);
        }
        return correctLetters.toString();
    }

    private void addGreenLetter(StringBuilder correctLetters, TextField letter) {
        String currentLetter = letter.getText();

        if (currentLetter.equals("")) {
            correctLetters.append("*");
        } else {
            correctLetters.append(currentLetter);
        }
    }

}