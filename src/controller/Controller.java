package controller;

import helper.ShiftCipher;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Controller {

    public Controller() {
        shiftCipher = new ShiftCipher();
    }

    private ShiftCipher shiftCipher;

    private int maxKeyLength = 10;

    private File filePlace;

    private String fileNameWithExtension;

    @FXML
    private TextField keyTextField;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    void initialize() {

        keyTextField.textProperty().addListener((Observable x) -> {
            if (keyTextField.getText().length() > maxKeyLength) {
                String s = keyTextField.getText().substring(0, maxKeyLength);
                keyTextField.setText(s);
            }
        });
    }

    @FXML
    public void encryptionButtonClicked() {
        outputTextArea.setText(getEncryptedText());
    }

    private String getEncryptedText(){
        String input = inputTextArea.getText();
        int keyValue = getKeyValue();

        return shiftCipher.encrypt(input, keyValue);
    }

    private int getKeyValue(){
        String keyText = keyTextField.getText();

        return shiftCipher.getKeyValue(keyText);
    }

    @FXML
    public void decryptionButtonClicked() {
        outputTextArea.setText(getDecryptedText());
    }

    private String getDecryptedText(){
        String input = inputTextArea.getText();
        int keyValue = getKeyValue();

        return shiftCipher.decrypt(input, keyValue);
    }

    @FXML
    void openMenuItemClicked() {

    }

    @FXML
    void saveMenuItemClicked() {
        chooseFilePlaceToSave();

        if(isFilePlaceChosen()) {
            createFileNameWithExtension("shc");
            writeTextInputToFile(outputTextArea, fileNameWithExtension);
        }
    }

    @FXML
    void closeMenuItemClicked() {
        Platform.exit();
    }

    @FXML
    void loadKeyMenuItemClicked() {
        chooseFilePlaceToOpen();

        if(isFilePlaceChosen()){
            loadKeyFromFile();
        }
    }

    @FXML
    void saveKeyMenuItemClicked() {
        chooseFilePlaceToSave();

        if(isFilePlaceChosen()) {
            createFileNameWithExtension("shk");
            writeTextInputToFile(keyTextField, fileNameWithExtension);
        }
    }

    private void loadKeyFromFile(){

        String key = "";

        try {
            key = new String(Files.readAllBytes(filePlace.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        keyTextField.setText(key);
    }

    private void chooseFilePlaceToOpen(){
        filePlace = new FileChooser().showOpenDialog(null);
    }

    private void chooseFilePlaceToSave(){
        filePlace = new FileChooser().showSaveDialog(null);
    }

    private boolean isFilePlaceChosen(){
        return filePlace != null;
    }

    private void createFileNameWithExtension(String extension){
        fileNameWithExtension = filePlace.toString() + "." + extension;
    }

    private void writeTextInputToFile(TextInputControl textInput, String file){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write(textInput.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
