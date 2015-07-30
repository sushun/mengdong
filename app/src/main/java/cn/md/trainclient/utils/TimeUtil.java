package cn.md.trainclient.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    // 获取当前时间
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }
    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate2(String sdate) {
        try {
            return dateFormater2.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以友好的方式显示时间
     *
     */
    public static String friendly_time(Date time) {
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }


    // 日期以月份显示
    // 2013-07-07：2013年7月
    public static String getMonthFriendly(String dateStr) {
        String date = "";
        if (dateStr.length() >= 10) {
            String[] dates = dateStr.substring(0, 10).split("-");

            if (dates[1].contains("0")) {
                dates[1] = dates[1].substring(1);
            }

            date = dates[0] + "年" + dates[1] + "月";
            return date;
        }

        return null;
    }




    //得到课程表可变参数
    public static String getChangeCouserUrl() {
        Date now = new Date();
        SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
        String[] strs = d1.format(now).split("-");
        int cYear = Integer.parseInt(strs[0]);
        int cMonth = Integer.parseInt(strs[1]);
        if (cMonth > 7) {
            return cYear + "-" + (cYear + 1) + "-" + 1;
        }
        return (cYear - 1) + "-" + cYear + "-" + 2;
    }



    // 得到今天是周几
    public static int getDayOfWeek(int year, int month, int day) {

        if (month == 1) {
            month = 13;
            year--;
        }
        if (month == 2) {
            month = 14;
            year--;
        }
        return (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year
                / 100 + year / 400) % 7 + 1;
    }

    /**
     * @return 返回符合clock格式当前时间
     */
    public static long getClockTestCurrentTime() {
        Date date = new Date();
        // format对象是用来以指定的时间格式格式化时间的
        SimpleDateFormat from = new SimpleDateFormat("yyyyMMddHHmm"); // 这里的格式可以自己设置
        // format()方法是用来格式化时间的方法
        String times = from.format(date);
        return Long.parseLong(times);
    }
    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    private static Date getDateAfterDay(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d   日期
     * @param min 提前的分钟
     * @return
     */
    private static Date getDateBeforeMin(Date d, int min) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        SimpleDateFormat from = new SimpleDateFormat("yyyyMMddHHmm");
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) - min);
        return now.getTime();
    }


}
