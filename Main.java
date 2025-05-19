package com.example.adrenexhome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // Global static variables accessible throughout the app
    public static Stage mainStage;              // Holds the main window stage
    public static String selectedLang = "en";   // Default selected language
    public static UserProfile currentUser;      // Currently logged-in user

    // JavaFX entry point – this method is called when the app starts
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        loadScene("language.fxml", "Choose Language"); // Load the first screen
    }

    // Utility method to load and switch between scenes
    public static void loadScene(String fxml, String title) {
        try {
            // Load the requested FXML file
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
            Scene scene = new Scene(loader.load(), 840, 600);  // Set scene size (w x h)

            // Add common stylesheet to apply CSS styles
            scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());

            // Set stage properties and show the window
            mainStage.setTitle(title);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            // Print any errors during scene loading
            e.printStackTrace();
        }
    }

    // Main method – required for launching the JavaFX application
    public static void main(String[] args) {
        launch(); // Calls the start() method internally
    }
}
