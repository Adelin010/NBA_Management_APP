package com.example.nba.menus;

import com.example.nba.model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.example.nba.controller.AppCtrl;
import java.util.List;

public class MenuGUI extends Application {
    private static AppCtrl controller;
    private Stage primaryStage;
    private StackPane contentArea;

    public static void setCrtl(AppCtrl appCtrl){
        controller = appCtrl;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();

        VBox sidebar = createSidebar();
        contentArea = new StackPane();
        contentArea.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85); -fx-background-radius: 10;");
        contentArea.setPadding(new Insets(15));

        root.setLeft(sidebar);
        root.setCenter(contentArea);
        BorderPane.setMargin(contentArea, new Insets(20));
        BorderPane.setMargin(sidebar, new Insets(20, 0, 20, 20));

        showDashboardOverview();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("NBA Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15;");

        Label title = new Label("NBA Management");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title.setStyle("-fx-text-fill: white;");

        String[] menuItems = {
                "Get Manager by name",
                "Add Team",
                "Remove Team",
                "Games Per Team",
                "Team Founds",
                "Sponsor Founds",
                "Filter Players by Age",
                "Winning Points Per Team",
                "Sort by age"
        };

        VBox buttons = new VBox(8);
        buttons.setAlignment(Pos.TOP_LEFT);
        buttons.setPadding(new Insets(10, 0, 0, 0));

        for (String item : menuItems) {
            Button btn = new Button(item);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
            btn.setOnAction(e -> handleMenuSelection(item));
            buttons.getChildren().add(btn);
        }

        sidebar.getChildren().addAll(title, new Separator(), buttons);
        return sidebar;
    }

    private void handleMenuSelection(String option) {
        switch (option) {
            case "Get Manager by name" -> showManagerSearchByName();
            case "Add Team" -> showAddTeamForm();
            case "Remove Team" -> showRemoveTeamForm();
            case "Games Per Team" -> showGamesPerTeam();
            case "Team Founds" -> showTeamFounds();
            case "Sponsor Founds" -> showSponsorFounds();
            case "Filter Players by Age" -> showFilterPlayersByAge();
            case "Winning Points Per Team" -> showWinningPointsPerTeam();
            case "Sort by age" -> showSortPlayersByAge();
        }
    }

    private void showDashboardOverview() {
        VBox dashboard = new VBox(15);
        dashboard.setAlignment(Pos.TOP_CENTER);

        Label header = new Label("NBA Dashboard Overview");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        dashboard.getChildren().addAll(header);
        contentArea.getChildren().setAll(dashboard);
    }

    private void showManagerSearchByName() {
        VBox form = createFormLayout("Search Manager by Name");

        TextField nameField = new TextField();
        nameField.setPromptText("Manager Name");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();

        searchButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                List<Manager> managers = controller.getManagerByName(name);
                StringBuilder result = new StringBuilder();
                managers.forEach(manager -> result.append(manager.toString()).append("\n"));
                resultLabel.setText(result.toString());
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(nameField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showAddTeamForm() {
        VBox form = createFormLayout("Add New Team");

        TextField nameField = new TextField();
        nameField.setPromptText("Team Name");

        TextField conferenceIdField = new TextField();
        conferenceIdField.setPromptText("Conference ID");

        Button submitButton = createButton("Add Team");
        Label messageLabel = new Label();

        submitButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int conferenceId = Integer.parseInt(conferenceIdField.getText());
                NBATeam team = new NBATeam(name, conferenceId);
                controller.addTeam(team);
                messageLabel.setText("Team added successfully!");
            } catch (Exception ex) {
                messageLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(nameField, conferenceIdField, submitButton, messageLabel);
        contentArea.getChildren().setAll(form);
    }
    private void showRemoveTeamForm() {
        VBox form = createFormLayout("Remove Team");

        TextField teamNameField = new TextField();
        teamNameField.setPromptText("Team Name");

        Button removeButton = createButton("Remove Team");
        Label messageLabel = new Label();

        removeButton.setOnAction(e -> {
            try {
                String teamName = teamNameField.getText();
                controller.removeTeamByName(teamName); // Call to the controller's remove method
                messageLabel.setText("Team '" + teamName + "' removed successfully!");
            } catch (Exception ex) {
                messageLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(teamNameField, removeButton, messageLabel);
        contentArea.getChildren().setAll(form);
    }
    private void showGamesPerTeam() {
        VBox form = createFormLayout("Games Per Team");

        TextField teamNameField = new TextField();
        teamNameField.setPromptText("Team Name");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();

        searchButton.setOnAction(e -> {
            try {
                String teamName = teamNameField.getText();
                List<Game> games = controller.getGamesPerTeam(teamName);
                StringBuilder result = new StringBuilder();
                games.forEach(game -> result.append(game.toString()).append("\n"));
                resultLabel.setText(result.toString());
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(teamNameField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showTeamFounds() {
        VBox form = createFormLayout("Team Founds");

        TextField teamNameField = new TextField();
        teamNameField.setPromptText("Team Name");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();

        searchButton.setOnAction(e -> {
            try {
                String teamName = teamNameField.getText();
                List<Found> founds = controller.getTeamFounds(teamName);
                StringBuilder result = new StringBuilder();
                founds.forEach(found -> result.append(found.toString()).append("\n"));
                resultLabel.setText(result.toString());
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(teamNameField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showSponsorFounds() {
        VBox form = createFormLayout("Sponsor Founds");

        TextField sponsorNameField = new TextField();
        sponsorNameField.setPromptText("Sponsor Name");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();

        searchButton.setOnAction(e -> {
            try {
                String sponsorName = sponsorNameField.getText();
                List<Found> founds = controller.getSponsorFounds(sponsorName);
                StringBuilder result = new StringBuilder();
                founds.forEach(found -> result.append(found.toString()).append("\n"));
                resultLabel.setText(result.toString());
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(sponsorNameField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showFilterPlayersByAge() {
        VBox form = createFormLayout("Filter Players by Age");

        TextField startAgeField = new TextField();
        startAgeField.setPromptText("Start Age");

        TextField endAgeField = new TextField();
        endAgeField.setPromptText("End Age");

        Button filterButton = createButton("Filter");
        Label resultLabel = new Label();

        filterButton.setOnAction(e -> {
            try {
                int startAge = Integer.parseInt(startAgeField.getText());
                int endAge = Integer.parseInt(endAgeField.getText());
                List<NBAPlayer> players = controller.filterPlayersByAge(startAge, endAge);
                StringBuilder result = new StringBuilder();
                players.forEach(player -> result.append(player.toString()).append("\n"));
                resultLabel.setText(result.toString());
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(startAgeField, endAgeField, filterButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showWinningPointsPerTeam() {
        VBox form = createFormLayout("Winning Points Per Team");

        Button fetchButton = createButton("Fetch");
        Label resultLabel = new Label();

        TextField nameField = new TextField();
        nameField.setPromptText("Enter the name of the team");

        fetchButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int result = controller.getPointsOfWinningTeam(name);
                resultLabel.setText("Points: %d".formatted(result));
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });
        form.getChildren().addAll(fetchButton, resultLabel, nameField);
        contentArea.getChildren().setAll(form);
    }

    private void showSortPlayersByAge() {
        VBox form = createFormLayout("Sort Players by Age");

        Button sortButton = createButton("Sort");
        Label resultLabel = new Label();

        sortButton.setOnAction(e -> {
            try {
                List<NBAPlayer> sortedPlayers = controller.sortPlayersByAge();
                StringBuilder result = new StringBuilder();
                sortedPlayers.forEach(player -> result.append(player.toString()).append("\n"));
                resultLabel.setText(result.toString());
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(sortButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private VBox createFormLayout(String title) {
        VBox form = new VBox(10);
        form.setAlignment(Pos.TOP_CENTER);

        Label header = new Label(title);
        header.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        form.getChildren().add(header);

        return form;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setMaxWidth(200);
        return button;
    }
}