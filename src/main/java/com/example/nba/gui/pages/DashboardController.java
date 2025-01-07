// package com.example.nba.gui.pages;

// import javafx.fxml.FXML;
// import javafx.scene.control.Label;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;

// public class DashboardController {

//     @FXML
//     private Label playerMenuBtn;

//     @FXML
//     private Label teamMenuBtn;

//     @FXML
//     private Label gameMenuBtn;

//     @FXML
//     private Label statsBtn;

//     @FXML
//     private Label schedulesBtn;

//     @FXML
//     private Label dashboardBtn;

//     @FXML
//     private Label settingsBtn;

//     @FXML
//     private Label helpBtn;

//     @FXML
//     private VBox mainContent;

//     private final PlayerMenuGUI playerMenu;
//     private final TeamMenuGUI teamMenu;
//     private final ManagerMenuGUI managerMenu;
//     private final AdvancedMenuGUI advancedMenu;

//     public DashboardController() {
//         String url = System.getenv("DB_URL");
//         Repo<Manager> managerRepo = new RepoDB<>(url, Manager.class, "Manager");
//         Repo<NBAPlayer> playerRepo = new RepoDB<>(url, NBAPlayer.class, "Player");
//         Repo<NBATeam> teamRepo = new RepoDB<>(url, NBATeam.class, "Team");
//         Repo<Game> gameRepo = new RepoDB<>(url, Game.class, "Game");
//         Repo<Conference> conferenceRepo = new RepoDB<>(url, Conference.class, "Conference");

//         ManagerService managerService = new ManagerService(managerRepo, teamRepo);
//         PlayerService playerService = new PlayerService(playerRepo, teamRepo);
//         TeamService teamService = new TeamService(teamRepo, conferenceRepo);
//         AdvancedService advancedService = new AdvancedService(gameRepo, playerRepo, teamRepo, managerRepo);

//         ManagerController managerController = new ManagerController(managerService);
//         PlayerController playerController = new PlayerController(playerService);
//         TeamController teamController = new TeamController(teamService);
//         AdvancedController advancedController = new AdvancedController(advancedService);

//         playerMenu = new PlayerMenuGUI(playerController);
//         teamMenu = new TeamMenuGUI(teamController);
//         managerMenu = new ManagerMenuGUI(managerController);
//         advancedMenu = new AdvancedMenuGUI(advancedController);
//     }
//     @FXML
//     private void initialize() {
//         dashboardBtn.setOnMouseClicked(e -> openDashboard());
//         playerMenuBtn.setOnMouseClicked(e -> openPlayerMenu());
//         teamMenuBtn.setOnMouseClicked(e -> openTeamMenu());
//         gameMenuBtn.setOnMouseClicked(e -> openGameMenu());
//         statsBtn.setOnMouseClicked(e -> openStatsMenu());
//         schedulesBtn.setOnMouseClicked(e -> openSchedulesMenu());
//         settingsBtn.setOnMouseClicked(e -> openSettings());
//         helpBtn.setOnMouseClicked(e -> openHelpCenter());
//     }
//     private void openDashboard() {
//         Stage stage = (Stage) mainContent.getScene().getWindow();
//         managerMenu.start(stage);
//     }
//     private void openPlayerMenu() {
//         Stage stage = (Stage) mainContent.getScene().getWindow();
//         playerMenu.start(stage);
//     }
//     private void openTeamMenu() {
//         Stage stage = (Stage) mainContent.getScene().getWindow();
//         teamMenu.start(stage);
//     }
//     private void openGameMenu() {
//         Stage stage = (Stage) mainContent.getScene().getWindow();
//         advancedMenu.start(stage);
//     }
//     private void openStatsMenu() {
//         Stage stage = (Stage) mainContent.getScene().getWindow();
//         advancedMenu.start(stage);
//     }
//     private void openSchedulesMenu() {
//         Stage stage = (Stage) mainContent.getScene().getWindow();
//         advancedMenu.start(stage);
//     }
// }