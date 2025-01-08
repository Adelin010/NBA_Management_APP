public class MainMenuGUI extends Application {
    private final Repo<Manager> rm;
    private final Repo<NBAPlayer> rp;
    private final Repo<NBATeam> rt;
    private final Repo<Game> rg;
    private final Repo<Conference> rc;

    private final ManagerController mc;
    private final TeamController tc;
    private final PlayerController pc;
    private final AdvancedController ac;

    private final PlayerService ps;
    private final TeamService ts;
    private final ManagerService ms;
    private final AdvancedService advS;

    private String userSelection;

    public MainMenuGUI() {
        String url = System.getenv("DB_URL");
        try {
            rm = new RepoMem<>(Manager.class);
            rp = new RepoFile<>(NBAPlayer.class, "PlayerData.txt");
            rt = new RepoDB<>(url, NBATeam.class, "Team");
            rg = new RepoMem<>(Game.class);
            rc = new RepoFile<>(Conference.class, "ConferenceData.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ms = new ManagerService(rm, rt);
        ps = new PlayerService(rp, rt);
        ts = new TeamService(rt, rc);
        advS = new AdvancedService(rg, rp, rt, rm);

        mc = new ManagerController(ms);
        pc = new PlayerController(ps);
        tc = new TeamController(ts);
        ac = new AdvancedController(advS);

        userSelection = "None";
    }

    @Override
    public void start(Stage primaryStage) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/ball.jpeg", 800, 600, false, true),
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
        ImageView logo = new ImageView("file:/C:/Users/blaja/Downloads/logo.png");
        logo.setFitWidth(150);
        logo.setPreserveRatio(true);
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
        mainLayout.getChildren().addAll(logo, layout);
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

    private void openInMemoryMenu(Stage stage) { /* Implement the logic for In-memory */ }
    private void openFileMenu(Stage stage) { /* Implement the logic for File */ }
    private void openDatabaseMenu(Stage stage) { /* Implement the logic for Database */ }

    public String getUserSelection() {
        return userSelection;
    }
    private void closeMenu(Stage stage)
    {
        stage.close();
    }

    public static void main(String[] args) { launch(args); }
}
