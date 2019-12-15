package com.github.kfang;

import com.opencsv.bean.CsvToBeanBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;


public class MainController {
    @FXML
    public ObservableList<SalesReceipt> transactionsTableItems;

    @FXML
    public ObservableList<SalesReceiptGroup> donorTableItems;

    @FXML
    TextField donorsFileTextField;

    @FXML
    TextField templateFileTextField;

    private Stage stage;
    private File templateFile;

    @FXML
    private void initialize() {
    }

    public void setState(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void onDonorsBrowse(ActionEvent event) throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(this.stage);
        if (file != null) {
            donorsFileTextField.setText(file.getAbsolutePath());
            List<SalesReceipt> receipts =  new CsvToBeanBuilder<SalesReceipt>(new FileReader(file))
                    .withType(SalesReceipt.class).build().parse();
            this.transactionsTableItems.removeAll();
            this.transactionsTableItems.addAll(receipts);

            List<SalesReceiptGroup> groups = SalesReceiptGroup.fromReceipts(receipts);
            this.donorTableItems.removeAll();
            this.donorTableItems.addAll(groups);
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

    @FXML
    public void onGenerate(ActionEvent event) throws FileNotFoundException {
        ApachePoi.writeReceipts(this.templateFile, this.donorTableItems);
    }
}
