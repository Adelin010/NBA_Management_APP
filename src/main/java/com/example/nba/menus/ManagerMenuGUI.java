package com.example.nba.menus;

import com.example.nba.controller.ManagerController;
import com.example.nba.model.Manager;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ManagerMenuGUI {
    private final ManagerController managerController;
    private Stage primaryStage;
    public ManagerMenuGUI(ManagerController mc) {this.managerController = mc;}
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
        primaryStage.setTitle("Manager Menu - NBA Management System");
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
        Label title = new Label("Manager Menu");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #f1c40f;");
        Text addManagerText = createSidebarText("Add Manager", () -> openAddManager(stage));
        Text viewManagerByIdText = createSidebarText("View Manager by ID", () -> openGetManagerById(stage));
        Text viewManagerByNameText = createSidebarText("View Manager by Name", () -> openGetManagerByName(stage));
        Text deleteManagerText = createSidebarText("Delete Manager", () -> openDeleteManager(stage));
        Text backButtonText = createSidebarText("Back to Main Menu", this::goToMainMenu);
        sidebar.getChildren().addAll(title, addManagerText, viewManagerByIdText, viewManagerByNameText, deleteManagerText, backButtonText);
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
    private void openAddManager(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Add Manager");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Manager Name");
        TextField teamIdField = new TextField();
        teamIdField.setPromptText("Enter Team ID");
        Button submitButton = new Button("Add Manager");
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        submitButton.setOnAction(e -> {
            try{
                String name = nameField.getText();
                int teamId = Integer.parseInt(teamIdField.getText());
                managerController.add(name, teamId);
                resultLabel.setText("Manager added successfully!");
            }catch (Exception ex) {
                resultLabel.setText("Error adding manager: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, nameField, teamIdField, submitButton, resultLabel);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openGetManagerById(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Get Manager by ID");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        TextField idField = new TextField();
        idField.setPromptText("Enter Manager ID");
        Button searchButton = new Button("Search");
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        searchButton.setOnAction(e -> {
            try{
                int id = Integer.parseInt(idField.getText());
                Manager manager = managerController.getById(id);
                resultLabel.setText(manager != null ? manager.toString() : "No manager found with ID " + id);
            }catch (Exception ex) {
                resultLabel.setText("Error fetching manager: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, idField, searchButton, resultLabel);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openGetManagerByName(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Get Manager by Name");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Manager Name");
        Button searchButton = new Button("Search");
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        searchButton.setOnAction(e -> {
            try{
                String name = nameField.getText();
                Manager manager = managerController.getByName(name);
                resultLabel.setText(manager != null ? manager.toString() : "No manager found with name " + name);
            }catch (Exception ex) {
                resultLabel.setText("Error fetching manager: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, nameField, searchButton, resultLabel);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openDeleteManager(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Delete Manager");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        TextField idField = new TextField();
        idField.setPromptText("Enter Manager ID to Delete");
        Button deleteButton = new Button("Delete");
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        deleteButton.setOnAction(e -> {
            try{
                int id = Integer.parseInt(idField.getText());
                managerController.delete(id);
                resultLabel.setText("Manager with ID " + id + " deleted successfully.");
            }catch (Exception ex) {
                resultLabel.setText("Error deleting manager: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, idField, deleteButton, resultLabel);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void goToMainMenu(){
        MainMenuGUI mainMenu = new MainMenuGUI();
        mainMenu.start(primaryStage);
    }
}
