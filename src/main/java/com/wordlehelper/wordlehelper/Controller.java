package com.wordlehelper.wordlehelper;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private HBox correctLettersContainer;

    @FXML
    private TextField includedLettersContainer;

    @FXML
    private TextField wrongLettersContainer;

    @FXML
    private AnchorPane answerWindow;

    @FXML
    private ListView<String> answerTextField;

    private Model model;
    private FadeTransition transition;

    public void initialize() {
        try {
            model = new Model();
            transition = new FadeTransition(Duration.millis(250), answerWindow);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void submit() {
        toggleAnswerWindow();
        ArrayList<String> answers = getAnswers();
        answerTextField.setItems(FXCollections.observableArrayList(answers));
    }

    public void back() {
        toggleAnswerWindow();
        clearTextFields();
    }

    private void toggleAnswerWindow() {
        if (!answerWindow.isDisable()) {
            System.out.println("Fading out");
            fadeAnswerWindow(1, 0);
            answerWindow.setDisable(true);
        } else {
            System.out.println("Fading in");
            answerWindow.setDisable(false);
            fadeAnswerWindow(0, 1);
        }
    }

    private void fadeAnswerWindow(double startOpacity, double endOpacity) {
        transition.setFromValue(startOpacity);
        transition.setToValue(endOpacity);
        transition.play();
    }

    private ArrayList<String> getAnswers() {
        String greenLetters = getGreenLetters();
        String yellowLetters = includedLettersContainer.getText();
        String greyLetters = wrongLettersContainer.getText();
        return model.getPossibleAnswers(greenLetters, yellowLetters, greyLetters);
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

    private void clearTextFields() {
        clearCorrectLetters();
        includedLettersContainer.clear();
        wrongLettersContainer.clear();
    }

    private void clearCorrectLetters() {
        for (Node correctLetter: correctLettersContainer.getChildren()) {
            ((TextField)correctLetter).clear();
        }
    }
}