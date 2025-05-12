package com.example.adrenexhome;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.*;

public class QuestionsController {

    // UI elements from FXML
    @FXML private ChoiceBox<Integer> daysChoice;
    @FXML private ChoiceBox<String> genderChoice;
    @FXML private ChoiceBox<String> goalChoice;

    // Initialize method is called automatically when the FXML is loaded
    @FXML
    public void initialize() {
        // Populate the dropdowns with options
        daysChoice.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6));
        genderChoice.setItems(FXCollections.observableArrayList("Male", "Female"));
        goalChoice.setItems(FXCollections.observableArrayList("Lose Weight", "Build Muscle", "Stay Fit"));
    }

    // Utility method to show alert messages
    private void alert(String message) {
        Alert a = new Alert(Alert.AlertType.WARNING, message);
        a.showAndWait();
    }

    // Called when the user clicks the Submit button
    @FXML
    protected void onSubmit() {
        String gender = genderChoice.getValue();
        String type = goalChoice.getValue();
        Integer days = daysChoice.getValue();

        // Check that all questions are answered
        if (gender == null || type == null || days == null) {
            alert("Please answer all questions.");
            return;
        }

        // Generate a new weekly program based on answers
        WeeklyProgram wp = new WeeklyProgram();
        wp.showProgramForDays(days, Main.selectedLang);
        String[] schedule = wp.getSchedule();

        // Save the new data in the user's profile
        UserProfile profile = Main.currentUser;
        profile.setGender(gender);
        profile.setTrainingType(type);
        profile.setWorkoutDays(days);
        profile.setSchedule(schedule);

        // Persist profile and move to the program view
        UserProfileManager.saveProfile(profile);
        Main.loadScene("program.fxml", "Your Program");
    }

    // Handle logout from the questions screen
    @FXML
    protected void onLogout() {
        Main.currentUser = null; // Clear user session
        Main.loadScene("login.fxml", "Login"); // Return to login screen
    }
}
