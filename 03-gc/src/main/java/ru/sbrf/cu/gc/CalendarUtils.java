package ru.sbrf.cu.gc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

class CalendarUtils {
    private static String dateFormat = "dd-MM-yyyy hh:mm:ss";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    static String ConvertMilliSecondsToFormattedDate(Long milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return simpleDateFormat.format(calendar.getTime());
    }
}
