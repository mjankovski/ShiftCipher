package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    private ShiftCipher shiftCipher;

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
    void initialize(){}

    @FXML
    public void cipherButtonClicked(){
        resultTextArea.setText(shiftCipher.encrypt(dataTextArea.getText(),shiftCipher.getKeyValue(keyTextField.getText())));
    }

    @FXML
    public void decipherButtonClicked(){
        resultTextArea.setText(shiftCipher.decrypt(dataTextArea.getText(),shiftCipher.getKeyValue(keyTextField.getText())));
    }

}
