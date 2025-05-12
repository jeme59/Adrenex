package com.example.adrenexhome;

/**
 * Represents a user's profile, including login info, personal data,
 * training preferences, and the generated workout schedule.
 */
public class UserProfile {
    // --- Private fields for user data ---
    private String email;         // Unique identifier for the user
    private String password;      // User's password
    private String firstName;     // User's first name
    private String lastName;      // User's last name
    private String gender;        // User's gender (e.g., "Male", "Female")
    private String trainingType;  // User's training goal (e.g., "Build Muscle")
    private int workoutDays;      // Number of days per week the user will train
    private String[] schedule;    // The generated weekly workout program

    // --- Default constructor required for JSON (Gson) deserialization ---
    public UserProfile() {}

    // --- Getters and Setters for each property ---

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public String getTrainingType() {

        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public int getWorkoutDays() {

        return workoutDays;
    }

    public void setWorkoutDays(int workoutDays) {

        this.workoutDays = workoutDays;
    }

    public String[] getSchedule() {

        return schedule;
    }

    public void setSchedule(String[] schedule) {

        this.schedule = schedule;
    }
}
