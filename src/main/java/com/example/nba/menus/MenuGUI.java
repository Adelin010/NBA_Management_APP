package com.example.nba.menus;

import java.util.List;

import com.example.nba.controller.Controller;
import com.example.nba.model.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuGUI extends Application{
    private final Controller controller;
    private Stage primaryStage;
    private StackPane contentArea;

    public MenuGUI(Controller controller) {
        this.controller = controller;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
    
        root.setBackground(new Background(new BackgroundImage(
                new Image("images/nba-court.jpeg", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT
        )));
        VBox sidebar = createSidebar();
        contentArea = new StackPane();
        contentArea.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85); -fx-background-radius: 10;");
        contentArea.setPadding(new Insets(15));
        contentArea.setMaxWidth(400);
        contentArea.setMaxHeight(450);

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
        sidebar.setPrefWidth(180);
        sidebar.setStyle("-fx-background-color: rgba(25, 25, 25, 0.9); -fx-background-radius: 10; -fx-padding: 15;");

        Label title = new Label("NBA Management");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title.setStyle("-fx-text-fill: white;");

        String[] menuItems = {
                "Dashboard Overview",
                "Add Found",
                "View Found by ID",
                "View All Founds",
                "Add Game",
                "View Game by ID",
                "Add Player",
                "View Player by ID",
                "Add Manager",
                "View Manager by ID",
                "Add Team",
                "View Team by ID"
        };

        VBox buttons = new VBox(8);
        buttons.setAlignment(Pos.TOP_LEFT);
        buttons.setPadding(new Insets(10, 0, 0, 0));

        for (String item : menuItems) {
            Button btn = new Button(item);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: CENTER-LEFT; -fx-font-size: 12px;");
            btn.setOnAction(e -> handleMenuSelection(item));
            buttons.getChildren().add(btn);
        }
        sidebar.getChildren().addAll(title, new Separator(), buttons);
        return sidebar;
    }

    private void handleMenuSelection(String option) {
        switch (option) {
            case "Dashboard Overview" -> showDashboardOverview();
            case "Add Found" -> showAddFoundForm();
            case "View Found by ID" -> showFoundSearchById();
            case "View All Founds" -> showAllFounds();
            case "Add Game" -> showAddGameForm();
            case "View Game by ID" -> showGameSearchById();
            case "Add Player" -> showAddPlayerForm();
            case "View Player by ID" -> showPlayerSearchById();
            case "Add Manager" -> showAddManagerForm();
            case "View Manager by ID" -> showManagerSearchById();
            case "Add Team" -> showAddTeamForm();
            case "View Team by ID" -> showTeamSearchById();
        }
    }

    private void showDashboardOverview() {
        VBox dashboard = new VBox(15);
        dashboard.setAlignment(Pos.TOP_CENTER);

        Label header = new Label("NBA Dashboard Overview");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        HBox statsBox = new HBox(10);
        statsBox.setAlignment(Pos.CENTER);
        statsBox.getChildren().addAll(
                createStatCard("Total Players", "200"),
                createStatCard("Total Teams", "30"),
                createStatCard("Total Managers", "50")
        );

        dashboard.getChildren().addAll(header, statsBox);
        contentArea.getChildren().setAll(dashboard);
    }

    private VBox createStatCard(String title, String value) {
        VBox card = new VBox(5);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setPrefWidth(110);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 12));
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    private void showAddFoundForm() {
        VBox form = createFormLayout("Add New Found");

        TextField amountField = new TextField();
        amountField.setMaxWidth(250);
        amountField.setPromptText("Found Amount");

        TextField dateField = new TextField();
        dateField.setMaxWidth(250);
        dateField.setPromptText("Date (YYYY-MM-DD)");

        ComboBox<String> teamComboBox = new ComboBox<>();
        teamComboBox.setMaxWidth(250);
        teamComboBox.setPromptText("Select Team");
        List<NBATeam> teams = controller.getAllTeams();
        teams.forEach(team -> teamComboBox.getItems().add(team.getName() + " (ID: " + team.getId() + ")"));

        ComboBox<String> sponsorComboBox = new ComboBox<>();
        sponsorComboBox.setMaxWidth(250);
        sponsorComboBox.setPromptText("Select Sponsor");
        List<Sponsor> sponsors = controller.getAllSponsors();
        sponsors.forEach(sponsor -> sponsorComboBox.getItems().add(sponsor.getName() + " (ID: " + sponsor.getId() + ")"));

        Button submitButton = createButton("Add Found");
        Label messageLabel = new Label();
        messageLabel.setWrapText(true);

        submitButton.setOnAction(e -> {
            try {
                long amount = Long.parseLong(amountField.getText());
                String date = dateField.getText();

                String teamSelection = teamComboBox.getValue();
                String sponsorSelection = sponsorComboBox.getValue();

                if (teamSelection == null || sponsorSelection == null) {
                    throw new IllegalArgumentException("Please select both team and sponsor");
                }

                int teamId = Integer.parseInt(teamSelection.substring(
                        teamSelection.indexOf("ID: ") + 4,
                        teamSelection.length() - 1
                ));

                int sponsorId = Integer.parseInt(sponsorSelection.substring(
                        sponsorSelection.indexOf("ID: ") + 4,
                        sponsorSelection.length() - 1
                ));

                Found found = new Found(sponsorId, teamId, amount);
                controller.addFound(found);
                messageLabel.setText("Found added successfully!");

                amountField.clear();
                dateField.clear();
                teamComboBox.setValue(null);
                sponsorComboBox.setValue(null);
            } catch (Exception ex) {
                messageLabel.setText("Error adding found: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(
                amountField,
                dateField,
                teamComboBox,
                sponsorComboBox,
                submitButton,
                messageLabel
        );
        contentArea.getChildren().setAll(form);
    }

    private void showFoundSearchById() {
        VBox form = createFormLayout("Search Found by ID");

        TextField idField = new TextField();
        idField.setMaxWidth(250);
        idField.setPromptText("Found ID");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();
        resultLabel.setWrapText(true);

        searchButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Found found = controller.getFoundById(id);
                resultLabel.setText(found != null ? found.toString() : "No found found with ID " + id);
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(idField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showAllFounds() {
        VBox listView = createListLayout("All Founds");

        for (Found found : controller.getAllFounds()) {
            listView.getChildren().add(new Label(found.toString()));
        }

        contentArea.getChildren().setAll(listView);
    }

    private void showAddGameForm() {
        VBox form = createFormLayout("Add New Game");

        TextField dateField = new TextField();
        dateField.setMaxWidth(250);
        dateField.setPromptText("Date (YYYY-MM-DD)");

        ComboBox<String> team1ComboBox = new ComboBox<>();
        team1ComboBox.setMaxWidth(250);
        team1ComboBox.setPromptText("Select Home Team");

        ComboBox<String> team2ComboBox = new ComboBox<>();
        team2ComboBox.setMaxWidth(250);
        team2ComboBox.setPromptText("Select Away Team");

        List<NBATeam> teams = controller.getAllTeams();
        teams.forEach(team -> {
            String teamDisplay = team.getName() + " (ID: " + team.getId() + ")";
            team1ComboBox.getItems().add(teamDisplay);
            team2ComboBox.getItems().add(teamDisplay);
        });

        TextField scoreTeam1Field = new TextField();
        scoreTeam1Field.setMaxWidth(250);
        scoreTeam1Field.setPromptText("Home Team Score");

        TextField scoreTeam2Field = new TextField();
        scoreTeam2Field.setMaxWidth(250);
        scoreTeam2Field.setPromptText("Away Team Score");

        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.setMaxWidth(250);
        typeComboBox.setPromptText("Game Type");
        typeComboBox.getItems().addAll("Regular", "Playoff", "Final");

        TextField seasonIdField = new TextField();
        seasonIdField.setMaxWidth(250);
        seasonIdField.setPromptText("Season ID");

        Button submitButton = createButton("Add Game");
        Label messageLabel = new Label();
        messageLabel.setWrapText(true);

        submitButton.setOnAction(e -> {
            try {
                if (dateField.getText().isEmpty() || team1ComboBox.getValue() == null ||
                        team2ComboBox.getValue() == null || scoreTeam1Field.getText().isEmpty() ||
                        scoreTeam2Field.getText().isEmpty() || typeComboBox.getValue() == null ||
                        seasonIdField.getText().isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled");
                }

                String team1Selection = team1ComboBox.getValue();
                String team2Selection = team2ComboBox.getValue();

                int team1Id = Integer.parseInt(team1Selection.substring(
                        team1Selection.indexOf("ID: ") + 4,
                        team1Selection.length() - 1
                ));
                int team2Id = Integer.parseInt(team2Selection.substring(
                        team2Selection.indexOf("ID: ") + 4,
                        team2Selection.length() - 1
                ));

                String date = dateField.getText();
                int scoreTeam1 = Integer.parseInt(scoreTeam1Field.getText());
                int scoreTeam2 = Integer.parseInt(scoreTeam2Field.getText());
                String type = typeComboBox.getValue();
                int seasonId = Integer.parseInt(seasonIdField.getText());

                Game game = new Game(date, scoreTeam1, scoreTeam2, team1Id, team2Id, type, seasonId);
                controller.addGame(game);
                messageLabel.setText("Game added successfully!");

                dateField.clear();
                team1ComboBox.setValue(null);
                team2ComboBox.setValue(null);
                scoreTeam1Field.clear();
                scoreTeam2Field.clear();
                typeComboBox.setValue(null);
                seasonIdField.clear();
            } catch (Exception ex) {
                messageLabel.setText("Error adding game: " + ex.getMessage());
            }
        });
        form.getChildren().addAll(
                dateField,
                team1ComboBox,
                scoreTeam1Field,
                team2ComboBox,
                scoreTeam2Field,
                typeComboBox,
                seasonIdField,
                submitButton,
                messageLabel
        );
        contentArea.getChildren().setAll(form);
    }

    private void showGameSearchById() {
        VBox form = createFormLayout("Search Game by ID");

        TextField idField = new TextField();
        idField.setMaxWidth(250);
        idField.setPromptText("Game ID");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();
        resultLabel.setWrapText(true);

        searchButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Game game = controller.getGameById(id);
                resultLabel.setText(game != null ? game.toString() : "No game found with ID " + id);
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(idField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showAddPlayerForm() {
        VBox form = createFormLayout("Add New Player");

        TextField nameField = new TextField();
        nameField.setMaxWidth(250);
        nameField.setPromptText("Player Name");

        TextField ageField = new TextField();
        ageField.setMaxWidth(250);
        ageField.setPromptText("Player Age");

        TextField salaryField = new TextField();
        salaryField.setMaxWidth(250);
        salaryField.setPromptText("Player Salary");

        TextField positionField = new TextField();
        positionField.setMaxWidth(250);
        positionField.setPromptText("Player Position");

        TextField pointsField = new TextField();
        pointsField.setMaxWidth(250);
        pointsField.setPromptText("Points");

        TextField reboundsField = new TextField();
        reboundsField.setMaxWidth(250);
        reboundsField.setPromptText("Rebounds");

        TextField assistsField = new TextField();
        assistsField.setMaxWidth(250);
        assistsField.setPromptText("Assists");

        TextField teamIdField = new TextField();
        teamIdField.setMaxWidth(250);
        teamIdField.setPromptText("Team ID");

        Button submitButton = createButton("Add Player");
        Label messageLabel = new Label();
        messageLabel.setWrapText(true);

        submitButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double salary = Double.parseDouble(salaryField.getText());
                String position = positionField.getText();
                int points = Integer.parseInt(pointsField.getText());
                int rebounds = Integer.parseInt(reboundsField.getText());
                int assists = Integer.parseInt(assistsField.getText());
                int teamId = Integer.parseInt(teamIdField.getText());

                NBAPlayer player = new NBAPlayer(name, age, salary, position, points, rebounds, assists, teamId);
                controller.addPlayer(player);
                messageLabel.setText("Player added successfully!");

                nameField.clear();
                ageField.clear();
                salaryField.clear();
                positionField.clear();
                pointsField.clear();
                reboundsField.clear();
                assistsField.clear();
                teamIdField.clear();
            } catch (Exception ex) {
                messageLabel.setText("Error adding player: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(
                nameField, ageField, salaryField, positionField,
                pointsField, reboundsField, assistsField, teamIdField,
                submitButton, messageLabel
        );
        contentArea.getChildren().setAll(form);
    }

    private void showPlayerSearchById() {
        VBox form = createFormLayout("Search Player by ID");

        TextField idField = new TextField();
        idField.setMaxWidth(250);
        idField.setPromptText("Player ID");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();
        resultLabel.setWrapText(true);

        searchButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                NBAPlayer player = controller.getPlayerById(id);
                resultLabel.setText(player != null ? player.toString() : "No player found with ID " + id);
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(idField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showAddManagerForm() {
        VBox form = createFormLayout("Add New Manager");

        TextField nameField = new TextField();
        nameField.setMaxWidth(250);
        nameField.setPromptText("Manager Name");

        TextField passwordField = new PasswordField();
        passwordField.setMaxWidth(250);
        passwordField.setPromptText("Password");

        TextField ageField = new TextField();
        ageField.setMaxWidth(250);
        ageField.setPromptText("Age");

        TextField teamIdField = new TextField();
        teamIdField.setMaxWidth(250);
        teamIdField.setPromptText("Team ID");

        Button submitButton = createButton("Add Manager");
        Label messageLabel = new Label();
        messageLabel.setWrapText(true);

        submitButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String password = passwordField.getText();
                int age = Integer.parseInt(ageField.getText());
                Integer teamId = Integer.parseInt(teamIdField.getText());
                Manager manager = new Manager(name, password, age, teamId);
                controller.addManager(manager);
                messageLabel.setText("Manager added successfully!");
                nameField.clear();
                passwordField.clear();
                ageField.clear();
                teamIdField.clear();
            } catch (Exception ex) {
                messageLabel.setText("Error adding manager: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(nameField, passwordField, ageField, teamIdField, submitButton, messageLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showManagerSearchById() {
        VBox form = createFormLayout("Search Manager by ID");

        TextField idField = new TextField();
        idField.setMaxWidth(250);
        idField.setPromptText("Manager ID");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();
        resultLabel.setWrapText(true);

        searchButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Manager manager = controller.getManagerById(id);
                resultLabel.setText(manager != null ? manager.toString() : "No manager found with ID " + id);
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(idField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showAddTeamForm() {
        VBox form = createFormLayout("Add New Team");

        TextField nameField = new TextField();
        nameField.setMaxWidth(250);
        nameField.setPromptText("Team Name");

        TextField conferenceIdField = new TextField();
        conferenceIdField.setMaxWidth(250);
        conferenceIdField.setPromptText("Conference ID");

        Button submitButton = createButton("Add Team");
        Label messageLabel = new Label();
        messageLabel.setWrapText(true);

        submitButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                Integer conferenceId = Integer.parseInt(conferenceIdField.getText());
                NBATeam team = new NBATeam(name, conferenceId);
                controller.addTeam(team);
                messageLabel.setText("Team added successfully!");
                nameField.clear();
                conferenceIdField.clear();
            } catch (Exception ex) {
                messageLabel.setText("Error adding team: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(nameField, conferenceIdField, submitButton, messageLabel);
        contentArea.getChildren().setAll(form);
    }

    private void showTeamSearchById() {
        VBox form = createFormLayout("Search Team by ID");

        TextField idField = new TextField();
        idField.setMaxWidth(250);
        idField.setPromptText("Team ID");

        Button searchButton = createButton("Search");
        Label resultLabel = new Label();
        resultLabel.setWrapText(true);

        searchButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                NBATeam team = controller.getTeamById(id);
                resultLabel.setText(team != null ? team.toString() : "No team found with ID " + id);
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });

        form.getChildren().addAll(idField, searchButton, resultLabel);
        contentArea.getChildren().setAll(form);
    }

    private VBox createFormLayout(String title) {
        VBox form = new VBox(10);
        form.setAlignment(Pos.TOP_CENTER);
        form.setMaxWidth(300);

        Label header = new Label(title);
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        form.getChildren().add(header);

        return form;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        button.setMaxWidth(250);
        return button;
    }

    private VBox createListLayout(String title) {
        VBox listView = new VBox(10);
        listView.setAlignment(Pos.TOP_CENTER);
        Label header = new Label(title);
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        listView.getChildren().add(header);
        return listView;
    }
}