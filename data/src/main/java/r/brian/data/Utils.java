package r.brian.data;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static Date getDateMonth(){
        return getDateMonth(false);
    }
    public static Date getDateMonth(boolean lessThan) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.YEAR);
        if (lessThan) {
            int currentMount = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);
            if (currentMount == 11) { //DECEMBER
                currentMount = 0; //JANUARY
                currentYear +=1; //NEXT YEAR
            } else {
                currentMount +=1;
            }
            calendar.set(Calendar.MONTH, currentMount);
            calendar.set(Calendar.YEAR, currentYear);
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = new Date(calendar.getTimeInMillis());
       // Log.e("UTILS DATE",date.toString());
        return date;
    }
}
