package cn.md.trainclient.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(String s) {
        if (null == s) return true;
        if (s.length() == 0) return true;
        if (s.trim().length() == 0) return true;
        return false;
    }
    
    public static String emptyStringIfNull(String s) {
        return (null == s) ? "" : s;
    }

    public static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = "KMGTPE".charAt(exp-1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
    
    public static String fixedLengthString(String s, int expectedLength) {
        int l = s.length();
        if (l >= expectedLength) {
            return s;
            //return s.substring(0, expectedLength);
        }
        for (int i = 0; i < expectedLength - l; i++) {
            s = s + " ";
        }
        return s;
    }
    
    public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

    /**
     * 是否是手机号码
     */
    public static boolean isPhoneNum(String content) {
    	Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");         
        Matcher m = p.matcher(content);   
        return m.matches();  
    }

    public static boolean isName(String content) {
        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{0,}$");
        Matcher m = p.matcher(content);
        return m.matches();
    }

    public static boolean isIDCard(String content) {
        Pattern p = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        Matcher m = p.matcher(content);
        return m.matches();
    }
}
