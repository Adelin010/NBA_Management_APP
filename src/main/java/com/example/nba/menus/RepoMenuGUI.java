package com.example.nba.menus;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


    

public class RepoMenuGUI extends Application {
    

    private String userSelection;


    @Override
    public void start(Stage primaryStage) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(RepoMenuGUI.class.getClassLoader().getResourceAsStream("images/MainMenuBack.jpg"), 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        VBox mainLayout = new VBox();
        mainLayout.setBackground(new Background(backgroundImage));
        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.2); " +
                        "-fx-border-color: rgba(255, 255, 255, 0.4); " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 15px; " +
                        "-fx-background-radius: 15px; " +
                        "-fx-padding: 20px; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 10, 0, 0, 4);"
        );
        // ImageView logo = new ImageView("file:/C:/Users/blaja/Downloads/logo.png");
        // logo.setFitWidth(150);
        // logo.setPreserveRatio(true);
        Text title = new Text("NBA Management System");
        title.setFont(Font.font("Arial", 24));
        title.setFill(Color.WHITE);

        Button inMemoryButton = createMenuButton("In-memory");
        Button fileButton = createMenuButton("File");
        Button databaseButton = createMenuButton("Database");
        Button quitButton = createQuitButton();

        inMemoryButton.setOnAction(e -> {
            userSelection = "In-memory";
            openInMemoryMenu(primaryStage);
        });
        fileButton.setOnAction(e -> {
            userSelection = "File";
            openFileMenu(primaryStage);
        });
        databaseButton.setOnAction(e -> {
            userSelection = "Database";
            openDatabaseMenu(primaryStage);
        });
        quitButton.setOnAction(e -> primaryStage.close());

        menuBox.getChildren().addAll(title, inMemoryButton, fileButton, databaseButton, quitButton);
        HBox layout = new HBox();
        layout.getChildren().add(menuBox);
        layout.setAlignment(Pos.CENTER_RIGHT);
        layout.setStyle("-fx-padding: 0 100 0 0;");
        mainLayout.getChildren().addAll( layout);
        mainLayout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setTitle("NBA Management System - Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.4); " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-cursor: hand;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: rgba(255, 0, 0, 1); " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-padding: 10px 20px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.4); " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-padding: 10px 20px;"
        ));
        return button;
    }

    private Button createQuitButton() {
        Button quitButton = new Button("Quit");
        quitButton.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-background-color: #FF4C4C; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-cursor: hand;"
        );
        quitButton.setOnMouseEntered(e -> quitButton.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-background-color: #FF6666; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-padding: 10px 20px;"
        ));
        quitButton.setOnMouseExited(e -> quitButton.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-background-color: #FF4C4C; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-padding: 10px 20px;"
        ));
        return quitButton;
    }

    private void openInMemoryMenu(Stage stage) { /* Implement the logic for In-memory */ stage.close();}
    private void openFileMenu(Stage stage) { /* Implement the logic for File */stage.close(); }
    private void openDatabaseMenu(Stage stage) { /* Implement the logic for Database */ stage.close(); }

    public String getUserSelection() {
        return userSelection;
    }
    public void closeMenu(Stage stage)
    {
        stage.close();
    }

}

