package com.example.nba.menus;

import com.example.nba.controller.*;
import com.example.nba.model.*;
import com.example.nba.service.*;
import com.example.nba.repo.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuGUI extends Application {
    private final PlayerMenuGUI pm;
    private final TeamMenuGUI tm;
    private final ManagerMenuGUI mm;

    private final Repo<Manager> rm;
    private final Repo<NBAPlayer> rp;
    private final Repo<NBATeam> rt;
    private final Repo<Game> rg;
    private final Repo<Conference> rc;

    private final ManagerController mc;
    private final TeamController tc;
    private final PlayerController pc;

    private final PlayerService ps;
    private final TeamService ts;
    private final ManagerService ms;

    public MainMenuGUI() {
        String url = "jdbc:sqlserver://localhost;databaseName=NBA;user=blaj;password=AndreiBlaj17;encrypt=true;trustServerCertificate=true;";
        try{
            rm = new RepoDB<>(url, Manager.class, "Manager");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        try{
            rp = new RepoDB<>(url, NBAPlayer.class, "Player");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        try{
            rt = new RepoDB<>(url, NBATeam.class, "Team");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        try{
            rg = new RepoDB<>(url, Game.class, "Game");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        try{
            rc = new RepoDB<>(url, Conference.class, "Conference");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        ms = new ManagerService(rm, rt);
        ps = new PlayerService(rp, rt);
        ts = new TeamService(rt, rc);

        mc = new ManagerController(ms);
        pc = new PlayerController(ps);
        tc = new TeamController(ts);

        pm = new PlayerMenuGUI(pc);
        tm = new TeamMenuGUI(tc);
        mm = new ManagerMenuGUI(mc);
    }
    @Override
    public void start(Stage primaryStage) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:/C:/Users/blaja/Downloads/MainMenuBack.jpg", 800, 600, false, true),
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
        Button playerButton = createMenuButton("Player Menu");
        Button teamButton = createMenuButton("Team Menu");
        Button managerButton = createMenuButton("Manager Menu");
        Button quitButton = createQuitButton();
        playerButton.setOnAction(e -> openPlayerMenu(primaryStage));
        teamButton.setOnAction(e -> openTeamMenu(primaryStage));
        managerButton.setOnAction(e -> openManagerMenu(primaryStage));
        quitButton.setOnAction(e -> primaryStage.close());
        menuBox.getChildren().addAll(title, playerButton, teamButton, managerButton, quitButton);
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
    private void openPlayerMenu(Stage stage) {pm.start(stage);}
    private void openTeamMenu(Stage stage) {tm.start(stage);}
    private void openManagerMenu(Stage stage) {mm.start(stage);}
    public static void main(String[] args) {launch(args);}
}
