package cn.md.trainclient.request;

import android.os.Handler;
import java.lang.reflect.Constructor;

public class RequestFactory {
    private static final String TAG = RequestFactory.class.getSimpleName();

    public final static <T extends RequestThread> T createRequestThread(
            Handler handler, Request request) {
        try {
            final Constructor<? extends RequestThread> constructor = ApiRequestThread.class
                    .getConstructor(Handler.class, Request.class);
            return (T) constructor.newInstance(handler, request);
        } catch (final Exception e) {
        }
        return null;
    }
}
