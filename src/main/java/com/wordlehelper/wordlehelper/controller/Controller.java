package com.wordlehelper.wordlehelper.controller;

import com.wordlehelper.wordlehelper.controller.controls.Transitions;
import com.wordlehelper.wordlehelper.model.Model;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    public void initialize() {
        try {
            model = new Model();

            initAllLetterContainers();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAllLetterContainers() {
        initCorrectLettersContainer();
        setTextFieldFormatting(includedLettersContainer, false);
        setTextFieldFormatting(wrongLettersContainer, false);
    }

    private void initCorrectLettersContainer() {
        for (Node correctLetterNode: correctLettersBox.getChildren()) {
            TextField correctLetterField = (TextField)correctLetterNode;
            setTextFieldFormatting(correctLetterField, true);
            correctLettersContainer.add(correctLetterField);
        }
    }

    private void setTextFieldFormatting(TextField textField, boolean correctLetterTextField) {
        TextFormatter<Object> textFieldFormatter = new TextFormatter<>((change -> {
            if (change.getText().isEmpty()) { return change; }
            if (!change.getText().matches("[A-Za-z]")) { return null; }

            change.setText(change.getText().toUpperCase());

            String newText = change.getControlNewText();
            if (correctLetterTextField) {
                if (newText.length() > 1) {
                    return null;
                }
            }
            return change;
        }));

        textField.setTextFormatter(textFieldFormatter);
    }

    public void submit() {
        Transitions.toggleAnswerWindow(answerWindow);
        ArrayList<String> answers = getAnswers();
        answerTextField.setItems(FXCollections.observableArrayList(answers));
    }

    public void startAgain() {
        Transitions.toggleAnswerWindow(answerWindow);
        clearTextFields();
    }

    public void keepGuessing() {
        Transitions.toggleAnswerWindow(answerWindow);
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