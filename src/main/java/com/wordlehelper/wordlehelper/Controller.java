package com.wordlehelper.wordlehelper;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private HBox correctLettersBox;
    private final ArrayList<TextField> correctLettersContainer = new ArrayList<>();

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
            initAllLetterContainers();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initAllLetterContainers() {
        initCorrectLettersContainer();
        setTextFieldToUpperCaseOnly(includedLettersContainer);
        setTextFieldToUpperCaseOnly(wrongLettersContainer);
    }

    private void initCorrectLettersContainer() {
        for (Node correctLetterNode: correctLettersBox.getChildren()) {
            TextField correctLetterField = (TextField)correctLetterNode;
            setTextFieldToUpperCaseOnly(correctLetterField);
            correctLettersContainer.add(correctLetterField);
        }
    }

    private void setTextFieldToUpperCaseOnly(TextField textField) {
        TextFormatter<Object> upperCaseFormatter = new TextFormatter<>((change -> {
            change.setText(change.getText().toUpperCase());
            String newText = change.getControlNewText();
            if (newText.length() > 1) {
                return null;
            }
            return change;
        }));

        textField.setTextFormatter(upperCaseFormatter);
    }

    public void submit() {
        toggleAnswerWindow();
        ArrayList<String> answers = getAnswers();
        answerTextField.setItems(FXCollections.observableArrayList(answers));
    }

    public void startAgain() {
        toggleAnswerWindow();
        clearTextFields();
    }

    public void keepGuessing() {
        toggleAnswerWindow();
    }

    private void toggleAnswerWindow() {
        if (!answerWindow.isDisable()) {
            fadeAnswerWindow(1, 0);
            answerWindow.setDisable(true);
        } else {
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
        StringBuilder correctLetters = new StringBuilder();
        for (TextField correctLetter: correctLettersContainer) {
            addGreenLetter(correctLetters, correctLetter);
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
        answerTextField.getItems().clear();
    }

    private void clearCorrectLetters() {
        for (TextField correctLetter: correctLettersContainer) {
            correctLetter.clear();
        }
    }
}