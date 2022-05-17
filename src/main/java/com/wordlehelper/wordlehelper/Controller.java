package com.wordlehelper.wordlehelper;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Controller {

    @FXML
    private HBox correctLettersContainer;

    @FXML
    private TextField includedLettersContainer;

    @FXML
    private TextField wrongLettersContainer;


    public void submit() {
        String greenLetters = getGreenLetters();
        String yellowLetters = includedLettersContainer.getText();
        String greyLetters = wrongLettersContainer.getText();
    }

    private String getGreenLetters() {
        ObservableList<Node> greenLetters = correctLettersContainer.getChildren();
        StringBuilder correctLetters = new StringBuilder();

        for (Node letter: greenLetters) {
            String currentLetter = ((TextField) letter).getText();

            if (currentLetter.equals("")) {
                correctLetters.append("*");
            } else {
                correctLetters.append(currentLetter);
            }
        }

        return correctLetters.toString();
    }

}