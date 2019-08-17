package me.brian.oeconomicis.utils;

public class Utils {

    public static String generateCategoryCode(String category) {
        return category.trim().replace(" ", "-");
    }
}
