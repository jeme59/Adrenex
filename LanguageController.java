package com.example.adrenexhome;

import javafx.fxml.FXML;

public class LanguageController {

    @FXML
    protected void onEnglishSelected() {
        Main.selectedLang = "en";
        Main.loadScene("login.fxml", "Login");
    }

    @FXML
    protected void onArabicSelected() {
        Main.selectedLang = "ar";
        Main.loadScene("login.fxml", "تسجيل الدخول");
    }

    @FXML
    protected void onHebrewSelected() {
        Main.selectedLang = "he";
        Main.loadScene("login.fxml", "התחברות");
    }
}
