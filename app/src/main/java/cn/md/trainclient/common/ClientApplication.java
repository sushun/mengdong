package cn.md.trainclient.common;

import android.app.Application;

/**
 * User: su
 * Date: 2015-07-09.
 */
public class ClientApplication extends Application {
    public static ClientApplication instance;

    public static ClientApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.instance = this;
    }

}
