package com.example.adrenexhome;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import java.awt.Desktop;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class VideosController {

    @FXML
    private VBox videoContainer;

    // خريطة لربط التمارين بروابط الفيديو
    private static final Map<String, String> videoLinks = new HashMap<>();

    static {
        videoLinks.put("Full Body", "https://www.youtube.com/shorts/S3ScjXa7Z2U");
        videoLinks.put("Upper", "https://www.youtube.com/shorts/PwbSPwxS-2o");
        videoLinks.put("Lower", "https://www.youtube.com/shorts/LOguA_x1YYM");
        videoLinks.put("Push", "https://www.youtube.com/shorts/hjgFpiqUwdY");
        videoLinks.put("Pull", "https://www.youtube.com/shorts/AdrHZ6q-ies");
        videoLinks.put("Legs", "https://www.youtube.com/shorts/E9TMx811jOI");
        videoLinks.put("Chest+Triceps", "https://www.youtube.com/watch?v=hkaHHE0AbXo&t=272s");
        videoLinks.put("Back+Biceps", "https://www.youtube.com/shorts/cG2kOuI4zMY");
        videoLinks.put("Shoulders", "https://www.youtube.com/shorts/D7mpmUSzGvo");
        videoLinks.put("Cardio+Abs", "https://www.youtube.com/shorts/5j_iF_oQFBI");
        videoLinks.put("Legs+Shoulders", "https://www.youtube.com/watch?v=legsandshoulders888");
        videoLinks.put("Cardio+Back", "https://www.youtube.com/watch?v=cardioback999");
    }

    @FXML
    public void initialize() {
        // عرض الفيديوهات المطابقة لجدول المستخدم الحالي
        videoContainer.getChildren().clear();

        if (Main.currentUser == null || Main.currentUser.getSchedule() == null)
            return;

        for (String workout : Main.currentUser.getSchedule()) {
            String url = videoLinks.getOrDefault(workout, null);
            if (url != null) {
                Hyperlink link = new Hyperlink("▶ " + workout);
                link.setOnAction(e -> openLink(url));
                videoContainer.getChildren().add(link);
            }
        }
    }

    @FXML
    private void goBack() {
        Main.loadScene("program.fxml", "Your Program");
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
