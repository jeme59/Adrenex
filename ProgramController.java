package com.example.adrenexhome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProgramController {

    @FXML
    private VBox scheduleContainer;  // Container where schedule labels will be added

    // This method runs automatically when the scene is loaded
    @FXML
    public void initialize() {
        // Get the current user from the global reference
        UserProfile profile = Main.currentUser;

        // If there's no logged-in user or no schedule, redirect to question screen
        if (profile == null || profile.getSchedule() == null) {
            Main.loadScene("questions.fxml", "Create Your Program");
            return;
        }

        // Load the user's current workout schedule
        String[] schedule = profile.getSchedule();

        // Clear previous labels (if any) and display each day with its workout
        scheduleContainer.getChildren().clear();
        for (int i = 0; i < schedule.length; i++) {
            Label dayLabel = new Label("Day " + (i + 1) + ": " + schedule[i]);
            dayLabel.setStyle("-fx-font-size: 16px;");
            scheduleContainer.getChildren().add(dayLabel);
        }
    }

    // Called when the user clicks the "Change Program" button
    @FXML
    protected void onChangeProgram() {
        UserProfile profile = Main.currentUser;
        if (profile == null) return;

        // Generate a new variant of the workout program based on previous answers
        WeeklyProgram wp = new WeeklyProgram();
        String[] newSchedule = wp.getNextProgram(profile.getWorkoutDays(), profile.getSchedule(), Main.selectedLang);

        // Save the updated schedule
        profile.setSchedule(newSchedule);
        UserProfileManager.saveProfile(profile);

        // Reload the program screen to reflect the changes
        Main.loadScene("program.fxml", "Updated Program");
    }

    // Called when the user clicks the "Logout" button
    @FXML
    protected void onLogout() {
        Main.currentUser = null; // Clear the session
        Main.loadScene("login.fxml", "Login"); // Redirect to login screen
    }
}
