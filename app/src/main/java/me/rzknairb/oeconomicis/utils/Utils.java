package me.rzknairb.oeconomicis.utils;

import android.text.format.DateFormat;

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

    public static String getYearMonthCode(Date date) {
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day          = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   date); // 06
        String year         = (String) DateFormat.format("yyyy", date); // 2013

        return String.format("%s %s", monthString, year);
    }
}
