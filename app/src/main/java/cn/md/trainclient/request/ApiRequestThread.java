package cn.md.trainclient.request;

import android.os.Handler;
import java.io.IOException;

import cn.md.trainclient.api.ApiException;
import cn.md.trainclient.client.ApiServiceClientManager;
import cn.md.trainclient.response.ApiResponse;

/**
 * Author: sushun
 * Date: 2015-07-09.
 */
public class ApiRequestThread extends RequestThread {

    public ApiRequestThread(Handler handler, Request apiRequest) {
        super(handler, apiRequest);
    }

    @Override
    protected void internalRun() throws ApiException, IOException {
        final ApiRequest request = (ApiRequest) getRequest();
        final ApiResponse response = ApiServiceClientManager.getInstance().getClient().execute(request);
        sendSuccessMessage(response);
    }
}
