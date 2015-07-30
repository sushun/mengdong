package cn.md.trainclient.request;

import android.content.Context;
import android.widget.Toast;

import cn.md.trainclient.R;
import cn.md.trainclient.api.ApiException;
import cn.md.trainclient.common.ClientApplication;
import cn.md.trainclient.response.ApiErrResult;
import cn.md.trainclient.response.NetErrResult;
import cn.md.trainclient.response.SuccessResult;

/**
 * Author: sushun
 * Date: 2015-07-09.
 */
public abstract class OnRequestResponseListener {
    protected Context context = ClientApplication.getInstance();

    private final static long TOAST_INTERVAL = 5 * 1000;    // 5 second.
    private static long lastApiErrorToastTime, lastNetErrorToastTime;

    /**
     * Called when response success.
     */
    public abstract void onRequestSuccess(long requestId, Request request, SuccessResult successResult);

    /**
     * Called when response apiError.
     */
    public void onRequestApiError(long requestId, Request request, ApiErrResult apiErrResult) {
        final ApiException.ApiError apiError = apiErrResult.getApiException().getApiError();
        String error = "";
        if (apiError != null) {
            final String message = apiError.getErrorMessage() == null ? "" : ":" + apiError.getErrorMessage();
            error = " " + apiError.getReasonCode().getErrorCode() + message;
        }

        final long now = System.currentTimeMillis();
        if (lastApiErrorToastTime + TOAST_INTERVAL < now) {
            Toast.makeText(context, context.getString(R.string.api_error) + error, Toast.LENGTH_SHORT).show();
            lastApiErrorToastTime = now;
        }
    }

    /**
     * Called when net error.
     */
    public void onRequestNetError(long requestId, Request request, NetErrResult netErrResult) {
        final long now = System.currentTimeMillis();
        if (lastNetErrorToastTime + TOAST_INTERVAL < now) {
            Toast.makeText(context, context.getString(R.string.net_error), Toast.LENGTH_SHORT).show();
            lastNetErrorToastTime = now;
        }
    }
}
