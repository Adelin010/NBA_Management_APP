package com.example.nba.gui.pages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/nba/gui/pages/login.fxml"));
        StackPane loginRoot = loader.load();

        Scene scene = new Scene(loginRoot);

        primaryStage.setScene(scene);
        primaryStage.setTitle("NBA Management Login");
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}
