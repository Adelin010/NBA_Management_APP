package com.example.nba.menus;
import javafx.fxml.FXML;
import javafx.stage.Stage;
public class DashboardController {
    private Stage primaryStage;
    @FXML
    private void openManagerMenu() {
        ManagerMenu managerMenu = new ManagerMenu(new java.util.Scanner(System.in), new com.example.nba.controller.ManagerController());
        new Thread(managerMenu::run).start();
    }
    @FXML
    private void handleLogout() {
        showLoginPage();
        ((Stage) Stage.getWindows().filtered(window -> window.isShowing()).get(0)).close();
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public static void showLoginPage() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(DashboardController.class.getResource("login.fxml"));
            javafx.scene.Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new javafx.scene.Scene(root, 400, 300));
            stage.setTitle("Login - NBA Management System");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
