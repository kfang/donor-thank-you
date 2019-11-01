package com.github.kfang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setState(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        List<SalesReceipt> receipts = SalesReceiptGenerator.generate(100);
        List<SalesReceiptGroup> groups = SalesReceiptGroup.fromReceipts(receipts);
        System.out.println(groups.size());
    }

    public static void main(String[] args) {
        launch();
    }

}

