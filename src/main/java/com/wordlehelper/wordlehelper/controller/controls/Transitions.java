package com.wordlehelper.wordlehelper.controller.controls;

import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Transitions {
    public static void toggleAnswerWindow(AnchorPane answerWindow) {
        if (!answerWindow.isDisable()) {
            fadeAnswerWindow(1, 0, answerWindow);
            answerWindow.setDisable(true);
        } else {
            answerWindow.setDisable(false);
            fadeAnswerWindow(0, 1, answerWindow);
        }
    }

    public static void fadeAnswerWindow(double startOpacity, double endOpacity, AnchorPane answerWindow) {
        FadeTransition transition = new FadeTransition(Duration.millis(250), answerWindow);
        transition.setFromValue(startOpacity);
        transition.setToValue(endOpacity);
        transition.play();
    }
}
