package cn.md.trainclient.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * User: su
 * Date: 2015-01-26.
 */
public class IntentUtils {
    /**
     * 拨打电话
     */
    public static void call(Context context, String mobileNum) {
        if (!StringUtils.isEmpty(mobileNum)) {
            Uri uri = Uri.parse("tel:" + mobileNum);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 发送短信
     */
    public static void sendSMS(Context context, String mobileNum) {
        Uri uri = Uri.parse("smsto:" + mobileNum);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开网页
     */
    public static void startWeb(Context context, String url) {
        if (!StringUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }
}
