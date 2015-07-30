package cn.md.trainclient.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApiServiceClientManager {

    private ApiServiceClient apiServiceClient;
    private Gson gson;

    public ApiServiceClientManager() {
        this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ;
    }

    ;

    private static class ApiServiceClientManagerHolder {
        private static ApiServiceClientManager instance = new ApiServiceClientManager();
    }

    public static ApiServiceClientManager getInstance() {
        return ApiServiceClientManagerHolder.instance;
    }

    public synchronized ApiServiceClient getClient() {
        if (apiServiceClient == null) {
            apiServiceClient = new ApiServiceClient(gson);
        }
        return apiServiceClient;
    }

}
