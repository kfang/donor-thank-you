package com.github.kfang;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
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

