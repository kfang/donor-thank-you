package com.github.kfang;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class MainController {
    @FXML
    TextField donorsFileTextField;
    @FXML TextField templateFileTextField;

    @FXML
    protected void onDonorsBrowse(ActionEvent event) {
        donorsFileTextField.setText("HELLO DONORS");
    }

    @FXML
    protected void onTemplateBrowse(ActionEvent event) {
        templateFileTextField.setText("HELLO TEMPLATE");
    }
}
