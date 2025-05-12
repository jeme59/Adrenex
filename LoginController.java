package com.example.adrenexhome;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    // Input fields for user credentials
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    // Called when the Login button is clicked
    @FXML
    protected void onLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Validate input
        if (email.isEmpty() || password.isEmpty()) {
            alert("Please enter email and password.");
            return;
        }

        // Load user profile from the JSON file using email
        UserProfile profile = UserProfileManager.loadProfile(email);

        // Check if profile exists and password matches
        if (profile == null || !profile.getPassword().equals(password)) {
            alert("Invalid credentials.");
            return;
        }

        // Store current user in the global context
        Main.currentUser = profile;

        // Redirect to appropriate screen:
        // If the user has no saved schedule → go to questions
        // Otherwise → show the weekly program
        if (profile.getSchedule() == null) {
            Main.loadScene("questions.fxml", "Create Your Program");
        } else {
            Main.loadScene("program.fxml", "Your Program");
        }
    }

    // Called when the "Create new account" hyperlink is clicked
    @FXML
    protected void goToRegister() {
        Main.loadScene("register.fxml", "Create Account");
    }

    // Utility function to show alert popups
    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
