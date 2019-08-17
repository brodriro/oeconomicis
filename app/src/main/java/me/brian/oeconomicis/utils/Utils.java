package me.brian.oeconomicis.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static String generateCategoryCode(String category) {
        return category.trim().replace(" ", "-");
    }
    public static Date randomDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(Calendar.MONTH, randBetween(0,11));
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.DAY_OF_MONTH, randBetween(1,29));
        return calendar.getTime();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
