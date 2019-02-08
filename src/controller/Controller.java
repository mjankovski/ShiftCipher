package controller;

import helper.ShiftCipher;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    private ShiftCipher shiftCipher;
    private int maxLength = 10;

    public Controller(){
        shiftCipher = new ShiftCipher();
    }

    @FXML
    private TextField keyTextField;

    @FXML
    private TextArea dataTextArea;

    @FXML
    private TextArea resultTextArea;

    @FXML
    void initialize(){

        keyTextField.textProperty().addListener((Observable x) -> {
                if (keyTextField.getText().length() > maxLength) {
                    String s = keyTextField.getText().substring(0, maxLength);
                    keyTextField.setText(s);
                }
        });
    }

    @FXML
    public void cipherButtonClicked(){
        resultTextArea.setText(shiftCipher.encrypt(dataTextArea.getText(),shiftCipher.getKeyValue(keyTextField.getText())));
    }

    @FXML
    public void decipherButtonClicked(){
        resultTextArea.setText(shiftCipher.decrypt(dataTextArea.getText(),shiftCipher.getKeyValue(keyTextField.getText())));
    }

    @FXML
    void openMenuItemClicked() {

    }

    @FXML
    void saveMenuItemClicked() {

    }

    @FXML
    void closeMenuItemClicked() {
        Platform.exit();
    }

}
