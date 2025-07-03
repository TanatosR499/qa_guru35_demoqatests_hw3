package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    static String dottedLocaleDatePattern = "dd.MM.yyyy";

    public String getDateInString(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public String getDayFromDate(Date date) {
        String dt = getDateInString(date, dottedLocaleDatePattern);
        String day = dt.substring(0, 2);
        return day.startsWith("0") ? day.replace("0","") : day;
    }

    public Integer getMonthValueFromDate(Date date) {
        LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDate.getMonthValue();
    }

    public String getYearFromDate(Date date) {
        String dt = getDateInString(date, dottedLocaleDatePattern);
        return dt.substring(6);
    }

    public String getMonthFromDate(Date date) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int monthNumber = getMonthValueFromDate(date);
        return months[monthNumber - 1];
    }
}
