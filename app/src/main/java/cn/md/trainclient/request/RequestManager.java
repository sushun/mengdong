package cn.md.trainclient.request;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import cn.md.trainclient.response.ApiErrResult;
import cn.md.trainclient.response.NetErrResult;
import cn.md.trainclient.response.RequestResult;
import cn.md.trainclient.response.SuccessResult;
import cn.md.trainclient.utils.Logger;

public class RequestManager {
    private final static String TAG = RequestManager.class.getSimpleName();

    private Map<Long, OnRequestResponseListener> listeners = new HashMap<>();
    private Map<Long, ProgressDialog> progressDialogs = new HashMap<>();
    private Map<Long, Request> requests = new HashMap<>();
    private final static ExecutorService threadPool = Executors.newFixedThreadPool(5);
/**/
    private static class RequestManagerHolder {
        private static RequestManager instance = new RequestManager();
    }

    public static RequestManager getInstance() {
        return RequestManagerHolder.instance;
    }

    public RequestManager() {}

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.obj == null) {
                return;
            }

            final Bundle bundle = (Bundle) msg.obj;
            final long requestId = bundle.getLong(RequestThread.BUNDLE_NAME_REQUEST_ID);
            final RequestResult result = (RequestResult) bundle.get(RequestThread.BUNDLE_NAME_RESULT);

            final Request request = requests.remove(requestId);
            final OnRequestResponseListener listener = listeners.remove(requestId);
            ProgressDialog progressDialog = progressDialogs.remove(requestId);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

            if ((listener != null) && (result instanceof NetErrResult)) {
                listener.onRequestNetError(requestId, request, (NetErrResult) result);
            } else if ((listener != null) && (result instanceof ApiErrResult)) {
                listener.onRequestApiError(requestId, request, (ApiErrResult) result);
            } else if ((listener != null) && (result instanceof SuccessResult)) {
                listener.onRequestSuccess(requestId, request, (SuccessResult) result);
            }
        }
    };

    /**
     * Starts a request thread.
     * @return requestId for this request. -1 means failed.
     */
    public synchronized long startRequest(OnRequestResponseListener listener, Request request) {
        final long existRequest = getDuplicatedRequest(request);
        if (existRequest > -1) {
            Logger.d(TAG, "duplicated request:" + request.getClass().getSimpleName());
            return existRequest;
        }

        Logger.i(TAG, "startRequest param: " + request.getClass().getSimpleName());

        final RequestThread thread = RequestFactory.createRequestThread(handler, request);
        if (thread != null) {
            final long requestId = thread.getRequestId();
            threadPool.execute(thread);
            listeners.put(requestId, listener);
            requests.put(requestId, request);
            return requestId;
        } else {
            Logger.e(TAG, "create thread failed: " + request.getClass().getSimpleName());
        }
        return -1;
    }

    Map<Long, Request> getRequests() {
        return requests;
    }

    // TODO : ?
    long getDuplicatedRequest(Request request) {
        for (final Map.Entry<Long, Request> entry : requests.entrySet()) {
            if (entry.getValue().isDuplicated(request)) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
