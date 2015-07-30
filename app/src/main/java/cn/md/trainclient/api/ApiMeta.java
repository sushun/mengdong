package cn.md.trainclient.api;

import cn.md.trainclient.request.ApiRequest;

import cn.md.trainclient.request.LoginRequest;
import cn.md.trainclient.response.ApiResponse;
import cn.md.trainclient.response.LoginResponse;

import java.util.HashMap;
import java.util.Map;

//默认使用get方式请求网络,如果要使用post在构造函数后面加入"post"
public enum ApiMeta {
    // JUST TEST
    GET_GUESS_COMIC("/handlerapp/senduserauthcode", TestRequest.class, TestResponse.class),
    LOGIN("mongduo/login?", LoginRequest.class, LoginResponse.class),
    QUERRY_COURSE_LIST("Course", CourseQuerryRequest.class, CourseQuerryResponse.class),
    QUERRY_COURSEWARE_LIST("Courseware",CourseWareQuerryRequest.class, CourseWareQuerryResponse.class);

    private final String uri;
    private final Class<? extends ApiRequest> requestType;
    private final Class<? extends ApiResponse> responseType;
    private static final Map<Class<? extends ApiRequest>, ApiMeta> metas;
    private String method = "get";

    static {
        metas = new HashMap<>();
        for (final ApiMeta meta : values()) {
            metas.put(meta.requestType, meta);
        }
    }

    public static ApiMeta getApiMeta(Class<? extends ApiRequest> requestType) {
        return metas.get(requestType);
    }

    private ApiMeta(String uri, Class<? extends ApiRequest> requestType, Class<? extends ApiResponse> responseType) {
        this.uri = uri;
        this.requestType = requestType;
        this.responseType = responseType;
    }

    private ApiMeta(String uri, Class<? extends ApiRequest> requestType, Class<? extends ApiResponse> responseType, String method) {
        this.uri = uri;
        this.requestType = requestType;
        this.responseType = responseType;
        this.method = method;
    }

    public Class<? extends ApiResponse> getResponseType() {
        return responseType;
    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }
}
