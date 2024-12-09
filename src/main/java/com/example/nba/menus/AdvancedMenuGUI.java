package com.example.nba.menus;

import com.example.nba.controller.AdvancedController;
import com.example.nba.model.Manager;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdvancedMenuGUI extends Application {
    private Stage primaryStage;
    private final AdvancedController advC;
    public AdvancedMenuGUI(AdvancedController advC) {this.advC = advC;}
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:/C:/Users/blaja/Downloads/PlayerBack.jpg", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        root.setBackground(new Background(backgroundImage));
        VBox sidebar = createAnimatedSidebar(primaryStage);
        root.setLeft(sidebar);
        StackPane mainContent = new StackPane();
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-background-radius: 15px;");
        root.setCenter(mainContent);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Advanced Menu - NBA Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private VBox createAnimatedSidebar(Stage stage) {
        VBox sidebar = new VBox(15);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setStyle(
                "-fx-background-color: rgba(46, 46, 46, 0.7);" +
                        "-fx-padding: 20px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-shadow: 4px 4px 15px rgba(0, 0, 0, 0.3);"
        );
        sidebar.setPrefWidth(220);
        Label title = new Label("Advanced Menu");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #f1c40f;");
        Text viewWinningManagerText = createSidebarText("View Winning Manager", () -> openViewWinningManager(stage));
        Text backButtonText = createSidebarText("Back to Main Menu", this::goToMainMenu);
        sidebar.getChildren().addAll(title, viewWinningManagerText, backButtonText);
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), sidebar);
        slideIn.setFromX(-220);
        slideIn.setToX(0);
        slideIn.play();
        return sidebar;
    }
    private Text createSidebarText(String text, Runnable action) {
        Text labelText = new Text(text);
        labelText.setStyle("-fx-font-size: 16px; -fx-fill: white; -fx-font-family: 'Arial';");
        labelText.setOnMouseEntered(e -> labelText.setStyle("-fx-fill: #f39c12; -fx-font-weight: bold;"));
        labelText.setOnMouseExited(e -> labelText.setStyle("-fx-fill: white; -fx-font-weight: normal;"));
        labelText.setOnMouseClicked(e -> action.run());
        return labelText;
    }
    private void openViewWinningManager(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Winning Manager");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        TextField gameIdField = new TextField();
        gameIdField.setPromptText("Enter Game ID");
        Button searchButton = new Button("Search");
        Button backButton = new Button("Back to Advanced Menu");
        backButton.setOnAction(e -> start(stage));
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        searchButton.setOnAction(e -> {
            try{
                int gameId = Integer.parseInt(gameIdField.getText());
                Manager manager = advC.managerWinningTeam(gameId);
                resultLabel.setText(manager != null ? "Winning Manager: " + manager.toString() : "No manager found for game ID " + gameId);
            }catch (Exception ex) {resultLabel.setText("Error fetching manager: " + ex.getMessage());}
        });
        layout.getChildren().addAll(header, gameIdField, searchButton, resultLabel, backButton);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void goToMainMenu() {
        MainMenuGUI mainMenu = new MainMenuGUI();
        mainMenu.start(primaryStage);
    }
}
