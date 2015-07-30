package cn.md.trainclient.request;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.md.trainclient.api.ApiException;
import cn.md.trainclient.common.ClientApplication;
import cn.md.trainclient.response.ApiErrResult;
import cn.md.trainclient.response.NetErrResult;
import cn.md.trainclient.response.RequestResult;
import cn.md.trainclient.response.Response;
import cn.md.trainclient.response.SuccessResult;
import cn.md.trainclient.utils.Logger;

import java.io.IOException;

public abstract class RequestThread implements Runnable {

    public static final String BUNDLE_NAME_REQUEST_ID = "BUNDLE_NAME_REQUEST_ID";
    public static final String BUNDLE_NAME_RESULT = "BUNDLE_NAME_RESULT";

    private Context context;
    protected Handler handler;
    protected Request request;

    private long requestId = nextId();
    private static long lastId = System.currentTimeMillis();

    private static final String TAG = RequestThread.class.getSimpleName();

    private static synchronized long nextId() {
        long now = System.currentTimeMillis();
        if (now <= lastId) {
            now = lastId + 1;
        }
        lastId = now;
        return now;
    }

    protected RequestThread(Handler handler, Request request) {
        this.handler = handler;
        this.request = request;
    }

    public long getRequestId() {
        return requestId;
    }

    @Override
    public final void run() {
        try {
            internalRun();
        } catch (final ApiException e) {
            Logger.d(TAG, "<<<< ApiException：" + e.getMessage());
            sendApiErrMessage(e);
        } catch (final IOException e) {
            Logger.d(TAG, "<<<< IOException：" + e.getMessage());
            sendNetErrMessage(e);
        }
    }

    protected abstract void internalRun() throws ApiException, IOException;

    protected Context getApplicationContext() {
        if (context == null) {
            context = ClientApplication.getInstance();
        }
        return context;
    }

    protected Request getRequest() {
        return request;
    }

    private void sendMessage(RequestResult result) {
        if (handler == null) {
            return;
        }

        final Message message = handler.obtainMessage();
        final Bundle bundle = new Bundle();
        bundle.putLong(BUNDLE_NAME_REQUEST_ID, requestId);
        if (result != null) {
            bundle.putSerializable(BUNDLE_NAME_RESULT, result);
        }
        message.obj = bundle;
        handler.sendMessage(message);
    }

    protected void sendNetErrMessage(Exception e) {
        sendMessage(new NetErrResult(e));
    }

    protected void sendSuccessMessage(Response response) {
        sendMessage(new SuccessResult(response));
    }

    protected void sendApiErrMessage(ApiException e) {
        sendMessage(new ApiErrResult(e));
    }
}
