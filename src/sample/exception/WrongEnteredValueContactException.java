package sample.exception;

import javafx.scene.control.Alert;

/**
 * Created by Artem on 02.04.2016.
 */
public class WrongEnteredValueContactException extends RuntimeException{
    public WrongEnteredValueContactException(String setTitle, String setHeaderText, String messageForIncorrectMistake) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(setTitle);
        alert.setHeaderText(setHeaderText);
        alert.setContentText(messageForIncorrectMistake);
        alert.show();
    }
}
