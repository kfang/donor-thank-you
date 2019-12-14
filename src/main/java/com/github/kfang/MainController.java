package com.github.kfang;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class MainController {
    @FXML
    TextField donorsFileTextField;

    @FXML
    TextField templateFileTextField;

    @FXML
    TableView transactionsTable;

    private Stage stage;
    private File donorsFile;
    private File templateFile;

    @FXML
    private void initialize() {

    }

    public void setState(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void onDonorsBrowse(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(this.stage);
        if (file != null) {
            donorsFileTextField.setText(file.getAbsolutePath());
            this.donorsFile = file;
        }
    }

    @FXML
    protected void onTemplateBrowse(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(this.stage);
        if (file != null) {
            templateFileTextField.setText(file.getAbsolutePath());
            this.templateFile = file;
        }
    }
}
