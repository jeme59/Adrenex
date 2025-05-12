package com.example.adrenexhome;

import java.util.Arrays;

/**
 * Generates and manages workout schedules based on user preferences.
 * Supports localized plans and variant switching (for 3-day plans).
 */
public class WeeklyProgram {
    private String[] schedule;

    /**
     * Generates the initial program for a given number of days.
     * Stores the result internally.
     *
     * @param days     how many days the user wants to train
     * @param langCode the selected language code ("en", "he", "ar")
     */
    public void showProgramForDays(int days, String langCode) {

        this.schedule = getProgram(days, 0, langCode);
    }

    /**
     * Gets the next available workout variant for 3-day programs.
     * Loops to the first variant if already on the last one.
     *
     * @param days     number of workout days
     * @param current  current schedule being used
     * @param langCode user's selected language
     * @return a different variant of the schedule, or the same if not 3-day
     */
    public String[] getNextProgram(int days, String[] current, String langCode) {
        if (days != 3) return current; // only variant support for 3-day schedules

        String[][] variants = getVariantsFor3Days(langCode);

        for (int i = 0; i < variants.length; i++) {
            if (Arrays.equals(variants[i], current)) {
                // Return the next variant in the list, loop back if at the end
                return variants[(i + 1) % variants.length];
            }
        }

        // Default fallback: first variant
        return variants[0];
    }

    /**
     * Core logic to return the schedule based on days and variant index.
     * For now, only 3-day plans support variants.
     */
    private String[] getProgram(int days, int variant, String langCode) {
        switch (days) {
            case 1:
                return localized(
                        new String[]{"Full Body"},
                        new String[]{"גוף מלא"},
                        new String[]{"جسم كامل"},
                        langCode
                );
            case 2:
                return localized(
                        new String[]{"Upper", "Lower"},
                        new String[]{"עֶלִיוֹן", "פלג גוף تحتון"},
                        new String[]{"جسم علوي", "جسم سفلي"},
                        langCode
                );
            case 3:
                return getVariantsFor3Days(langCode)[variant]; // Supports variants
            case 4:
                return localized(
                        new String[]{"Chest+Triceps", "Back+Biceps", "Legs", "Shoulders"},
                        new String[]{"חזה+תלת ראשי", "גב+דו ראשי", "רגליים", "כתפיים"},
                        new String[]{"صدر+تراي", "ظهر+باي", "رجل", "كتف"},
                        langCode
                );
            case 5:
                return localized(
                        new String[]{"Chest+Triceps", "Back+Biceps", "Legs", "Shoulders", "Cardio+Abs"},
                        new String[]{"חזה+תלת ראשי", "גב+דו ראשי", "רגליים", "כתפיים", "קרדיו+בטן"},
                        new String[]{"صدر+تراي", "ظهر+باي", "رجل", "كتف", "كارديو+معدة"},
                        langCode
                );
            case 6:
                return localized(
                        new String[]{"Chest+Triceps", "Back+Biceps", "Legs", "Shoulders", "Chest+Triceps", "Cardio+Abs"},
                        new String[]{"חזה+תלת ראשי", "גב+דו ראשי", "רגליים", "כתפיים", "חזה+תלת ראשי", "קרדيو+בטן"},
                        new String[]{"صدر+تراي", "ظهر+باي", "رجل", "كتف", "صدر+تراي", "كارديو+معدة"},
                        langCode
                );
            default:
                return new String[]{"Full Body"}; // fallback default
        }
    }

    /**
     * Returns all 3-day variants localized to a specific language.
     */
    private String[][] getVariantsFor3Days(String langCode) {
        return new String[][]{
                // Variant 1
                localized(
                        new String[]{"Push", "Pull", "Legs"},
                        new String[]{"לִדחוֹף", "מְשׁוֹך", "רגליים"},
                        new String[]{"دفع", "سحب", "رجل"},
                        langCode
                ),
                // Variant 2
                localized(
                        new String[]{"Chest+Triceps", "Back+Biceps", "Legs+Shoulders"},
                        new String[]{"חזה+תלת ראשי", "גב+דו ראשי", "רגליים+כתפיים"},
                        new String[]{"صدر+تراي", "ظهر+باي", "رجل+كتف"},
                        langCode
                ),
                // Variant 3
                localized(
                        new String[]{"Push", "Legs", "Cardio+Back"},
                        new String[]{"לִדחוֹף", "רגליים", "קרדיו+גב"},
                        new String[]{"دفع", "رجل", "كارديو+ظهر"},
                        langCode
                )
        };
    }

    /**
     * Helper to return the correct language version of the schedule.
     */
    private String[] localized(String[] en, String[] he, String[] ar, String langCode) {
        switch (langCode) {
            case "en": return en;
            case "he": return he;
            case "ar": return ar;
            default: return en;
        }
    }

    /**
     * Returns the internally stored schedule.
     */
    public String[] getSchedule() {
        return schedule;
    }
}
