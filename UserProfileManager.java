package com.example.adrenexhome;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;//ttt
import java.lang.reflect.Type;//ttt
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles saving, loading, and updating user profiles.
 * It persists data to a local JSON file using Gson for serialization.
 */
public class UserProfileManager {
    // The name of the file where user profiles are saved
    private static final String PROFILE_FILE = "profiles.json";

    // Gson instance for converting Java objects to JSON and vice versa
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // TypeToken for deserializing a list of UserProfile objects
    private static final Type USER_LIST_TYPE = new TypeToken<List<UserProfile>>() {}.getType();

    /**
     * Saves a new or updated user profile.
     * If the profile already exists (by email), it updates it.
     * If it doesn't exist, it adds it to the list.
     */
    public static void saveProfile(UserProfile newProfile) {
        List<UserProfile> profiles = loadAllProfiles();

        boolean updated = false;
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getEmail().equalsIgnoreCase(newProfile.getEmail())) {
                profiles.set(i, newProfile); // Update existing profile
                updated = true;
                break;
            }
        }

        if (!updated) {
            profiles.add(newProfile); // Add new profile
        }

        saveProfiles(profiles); // Save updated list to file
    }

    /**
     * Loads a user profile based on the given email.
     * @param email The user's email
     * @return The UserProfile if found, or null
     */
    public static UserProfile loadProfile(String email) {
        List<UserProfile> profiles = loadAllProfiles();
        for (UserProfile profile : profiles) {
            if (profile.getEmail().equalsIgnoreCase(email)) {
                return profile;
            }
        }
        return null;
    }

    /**
     * Loads all user profiles from the JSON file.
     * @return List of UserProfile objects
     */
    public static List<UserProfile> loadAllProfiles() {
        File file = new File(PROFILE_FILE);
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, USER_LIST_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Updates an existing profile or adds it if it doesn't exist.
     * Replaces any existing profile with the same email.
     */
    public static void updateOrAddUser(UserProfile profile) {
        List<UserProfile> profiles = loadAllProfiles();
        profiles.removeIf(p -> p.getEmail().equalsIgnoreCase(profile.getEmail())); // Remove if exists
        profiles.add(profile); // Add updated/new profile
        saveProfiles(profiles); // Save to file
    }

    /**
     * Saves the full list of user profiles to the JSON file.
     * @param profiles List of UserProfile objects
     */
    private static void saveProfiles(List<UserProfile> profiles) {
        try (FileWriter writer = new FileWriter(PROFILE_FILE)) {
            gson.toJson(profiles, writer); // Convert to JSON and write
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
