// package com.example.nba.gui.pages;

// import javafx.fxml.FXML;
// import javafx.scene.control.Label;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
// import javafx.stage.Stage;

// public class LoginController {
//     @FXML
//     private TextField usernameField;
//     @FXML
//     private PasswordField passwordField;
//     @FXML
//     private Label loginStatusLabel;
//     private final String validUsername = "admin";
//     private final String validPassword = "password123";
//     private Stage primaryStage;
//     @FXML
//     private void handleLogin() {
//         String username = usernameField.getText();
//         String password = passwordField.getText();
//         if (username.equals(validUsername) && password.equals(validPassword)) {
//             loginStatusLabel.setText("Login successful!");
//             loginStatusLabel.setStyle("-fx-text-fill: green;");
//             showMainMenu();
//         } else {
//             loginStatusLabel.setText("Invalid username or password!");
//             loginStatusLabel.setStyle("-fx-text-fill: red;");
//         }
//     }
//     private void showMainMenu() {
//         try {
//             javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("dashboard.fxml"));
//             javafx.scene.Parent root = loader.load();
//             DashboardController dashboardController = loader.getController();
//             dashboardController.setPrimaryStage(primaryStage);  // Set the primary stage for later use (logout, etc.)
//             primaryStage.setScene(new javafx.scene.Scene(root, 400, 300));
//             primaryStage.setTitle("Dashboard");
//             primaryStage.show();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
//     public void setPrimaryStage(Stage primaryStage) {
//         this.primaryStage = primaryStage;
//     }
// }
