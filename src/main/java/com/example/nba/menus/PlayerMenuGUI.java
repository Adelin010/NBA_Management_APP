package com.example.nba.menus;
import com.example.nba.controller.PlayerController;
import com.example.nba.error.IdOutOfRangeException;
import com.example.nba.error.InexistenteInstance;
import com.example.nba.model.NBAPlayer;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;
public class PlayerMenuGUI {
    private final PlayerController playerController;
    private Stage primaryStage;

    public PlayerMenuGUI(PlayerController pc) {this.playerController = pc;}
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/nba-court.jpeg", 800, 600, false, true),
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
        primaryStage.setTitle("Player Menu - NBA Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createAnimatedSidebar(Stage stage) {
        VBox sidebar = new VBox(15);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setStyle(
                "-fx-background-color: rgba(46, 46, 46, 0.7); " +
                        "-fx-padding: 20px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-shadow: 4px 4px 15px rgba(0, 0, 0, 0.3);"
        );
        sidebar.setPrefWidth(220);
        Label title = new Label("Player Menu");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #f1c40f;");
        Text addPlayerText = createSidebarText("Add Player", () -> openAddPlayer(stage));
        Text viewPlayerByIdText = createSidebarText("View Player by ID", () -> openViewPlayerById(stage));
        Text viewPlayerByNameText = createSidebarText("View Player by Name", () -> openViewPlayerByName(stage));
        Text deletePlayerText = createSidebarText("Delete Player", () -> openDeletePlayer(stage));
        Text sortByAgeText = createSidebarText("Sort Players by Age", () -> openSortByAge(stage));
        Text backButtonText = createSidebarText("Back to Main Menu", this::goToMainMenu);
        sidebar.getChildren().addAll(title, addPlayerText, viewPlayerByIdText, viewPlayerByNameText, deletePlayerText, sortByAgeText, backButtonText);
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
    private void openAddPlayer(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Add Player");
        header.setFont(new Font("Arial", 22));
        header.setStyle("-fx-text-fill: #f1c40f;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Player Name");
        nameField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-background-radius: 8px;");
        TextField ageField = new TextField();
        ageField.setPromptText("Enter Player Age");
        ageField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-background-radius: 8px;");
        TextField teamIdField = new TextField();
        teamIdField.setPromptText("Enter Team ID");
        teamIdField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-background-radius: 8px;");
        Button submitButton = new Button("Add Player");
        submitButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px; -fx-background-radius: 10px;");
        Button backButton = new Button("Back to Player Menu");
        backButton.setOnAction(e -> start(stage));
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        submitButton.setOnAction(e -> {
            try{
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                int teamId = Integer.parseInt(teamIdField.getText());
                playerController.add(name, age, 0.0, "Default Position", 0, 0, 0, teamId);
                resultLabel.setText("Player added successfully!");
            }catch(InexistenteInstance ex) {resultLabel.setText("Error: " + ex.getMessage());
            }catch(NumberFormatException ex) {resultLabel.setText("Invalid input format.");
            }catch (Exception ex) {resultLabel.setText("Error adding player: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, nameField, ageField, teamIdField, submitButton, resultLabel, backButton);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 15px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openViewPlayerById(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("View Player by ID");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        TextField idField = new TextField();
        idField.setPromptText("Enter Player ID");
        Button searchButton = new Button("Search");
        Button backButton = new Button("Back to Player Menu");
        backButton.setOnAction(e -> start(stage));
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        searchButton.setOnAction(e -> {
            try{
                int id = Integer.parseInt(idField.getText());
                NBAPlayer player = playerController.getById(id);
                resultLabel.setText(player != null ? player.toString() : "No player found with ID " + id);
            }catch (Exception ex) {
                resultLabel.setText("Error fetching player: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, idField, searchButton, resultLabel, backButton);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openViewPlayerByName(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("View Player by Name");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Player Name");
        Button searchButton = new Button("Search");
        Button backButton = new Button("Back to Player Menu");
        backButton.setOnAction(e -> start(stage));
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        searchButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                List<NBAPlayer> players = playerController.getByName(name);
                if (players != null && !players.isEmpty()) {
                    StringBuilder result = new StringBuilder();
                    for (NBAPlayer player : players) {
                        result.append(player.toString()).append("\n");
                    }
                    resultLabel.setText(result.toString());
                } else {
                    resultLabel.setText("No players found with name " + name);
                }
            } catch (Exception ex) {
                resultLabel.setText("Error fetching players: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, nameField, searchButton, resultLabel, backButton);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openDeletePlayer(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Delete Player");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        TextField idField = new TextField();
        idField.setPromptText("Enter Player ID to Delete");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back to Player Menu");
        backButton.setOnAction(e -> start(stage));
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        deleteButton.setOnAction(e -> {
            try{
                int id = Integer.parseInt(idField.getText());
                playerController.delete(id);
                resultLabel.setText("Player deleted successfully.");
            }catch (IdOutOfRangeException ex) {resultLabel.setText("Error: " + ex.getMessage());
            }catch (Exception ex) {
                resultLabel.setText("Error deleting player: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, idField, deleteButton, resultLabel, backButton);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void openSortByAge(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label header = new Label("Sort Players by Age");
        header.setFont(new Font("Arial", 18));
        header.setStyle("-fx-text-fill: white;");
        Button sortButton = new Button("Sort");
        Button backButton = new Button("Back to Player Menu");
        backButton.setOnAction(e -> start(stage));
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: white;");
        sortButton.setOnAction(e -> {
            try{
                List<NBAPlayer> sortedPlayers = playerController.sortByAge();
                StringBuilder result = new StringBuilder();
                for (NBAPlayer player : sortedPlayers) {
                    result.append(player.toString()).append("\n");
                }
                resultLabel.setText(result.toString());
            }catch (Exception ex) {
                resultLabel.setText("Error sorting players: " + ex.getMessage());
            }
        });
        layout.getChildren().addAll(header, sortButton, resultLabel, backButton);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-background-radius: 10px;");
        stage.setScene(new Scene(layout, 400, 300));
    }
    private void goToMainMenu(){
        MainMenuGUI mainMenu = new MainMenuGUI();
        mainMenu.start(primaryStage);
    }
}
