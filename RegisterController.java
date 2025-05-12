package com.example.adrenexhome;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterController {

    // FXML fields bound to the UI input fields in register.fxml
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    /**
     * Triggered when the user clicks the "Register" button.
     * Validates input and creates a new UserProfile if the email isn't taken.
     */
    @FXML
    protected void onRegister() {
        // Read and trim user input
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Basic validation to ensure no field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            alert("Please fill in all fields.");
            return;
        }

        // Check if a profile already exists for the given email
        if (UserProfileManager.loadProfile(email) != null) {
            alert("An account already exists with this email.");
            return;
        }

        // Create and populate a new UserProfile instance
        UserProfile newProfile = new UserProfile();
        newProfile.setFirstName(firstName);
        newProfile.setLastName(lastName);
        newProfile.setEmail(email);
        newProfile.setPassword(password);

        // Save the new profile to profiles.json
        UserProfileManager.saveProfile(newProfile);

        // Notify user and redirect to login screen
        alert("Account created. You can now log in.");
        Main.loadScene("login.fxml", "Login");
    }

    /**
     * Navigates back to the login screen if user clicks the "Back to Login" link.
     */
    @FXML
    protected void goToLogin() {
        Main.loadScene("login.fxml", "Login");
    }

    /**
     * Displays an information alert with the provided message.
     */
    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
