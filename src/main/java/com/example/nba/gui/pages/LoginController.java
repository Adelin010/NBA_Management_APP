package com.example.nba.gui.pages;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginStatusLabel;
    @FXML
    private Button loginButton;
    private static final String VALID_USERNAME = "user123";
    private static final String VALID_PASSWORD = "password123";
    @FXML
    public void initialize() {
        loginButton.setOnAction(this::handleLogin);
        loginStatusLabel.setText("");
    }
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        if (validateInput(username, password)) {
            if (authenticateUser(username, password)) {
                try {
                    loadDashboard();
                } catch (IOException e) {
                    loginStatusLabel.setText("Error loading dashboard: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                loginStatusLabel.setText("Invalid username or password");
            }
        }
    }
    private boolean validateInput(String username, String password) {
        if (username.isEmpty()) {
            loginStatusLabel.setText("Username is required");
            return false;
        }
        if (password.isEmpty()) {
            loginStatusLabel.setText("Password is required");
            return false;
        }
        return true;
    }
    private boolean authenticateUser(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }
    private void loadDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/nba/gui/pages/dashboard.fxml"));
        Parent dashboardRoot = loader.load();
        DashboardController dashboardController = loader.getController();
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        Scene dashboardScene = new Scene(dashboardRoot);
        currentStage.setScene(dashboardScene);
        currentStage.setMaximized(true);
        currentStage.show();
    }
}