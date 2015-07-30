package cn.md.trainclient.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * User: su
 * Date: 2015-03-26.
 */
public class DateUtils {
    private final static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormat2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };

    public static String formatToDoAlarmTime(Calendar calendar) {
        Calendar currentCalendar = Calendar.getInstance();

        Logger.d("", "<<<< YEAR:" + calendar.get(Calendar.YEAR) + ", MONTH: " + calendar.get(Calendar.MONTH) + ", DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH)) ;
        LocalDate date = new LocalDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate currentDate = new LocalDate(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DAY_OF_MONTH));
        int days = Days.daysBetween(currentDate, date).getDays();

        String dateTimeStr;
        DateTime dateTime = new DateTime(calendar.getTimeInMillis());

        if (days == 0) {
            dateTimeStr = "今天 " + dateTime.toString("HH:mm");
        } else if (days == 1) {
            dateTimeStr = "明天 " + dateTime.toString("HH:mm");
        } else {
            dateTimeStr = dateTime.toString("EE MM月dd日 HH:mm", Locale.CHINESE);
        }

        return dateTimeStr;
    }

    public static String formatToDoAlarmTime(long alarmTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(alarmTime);

        Calendar currentCalendar = Calendar.getInstance();

        LocalDate date = new LocalDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate currentDate = new LocalDate(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DAY_OF_MONTH));
        int days = Days.daysBetween(currentDate, date).getDays();

        String dateTimeStr;
        DateTime dateTime = new DateTime(calendar.getTimeInMillis());

        if (days == 0) {
            dateTimeStr = "今天 " + dateTime.toString("HH:mm");
        } else if (days == 1) {
            dateTimeStr = "明天 " + dateTime.toString("HH:mm");
        } else {
            dateTimeStr = dateTime.toString("EE MM月dd日 HH:mm", Locale.CHINESE);
        }

        return dateTimeStr;
    }

    /**
     * 以友好的方式显示时间
     */
    public static String format2FriendlyTime(long timeMillis) {
        Date time = new Date(timeMillis);
        Calendar cal = Calendar.getInstance();
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            return dateFormat2.get().format(time);
        } else if (days == 1) {
            return "昨天" + dateFormat2.get().format(time);
        } else {
            return dateFormat.get().format(time);
        }
    }
}
