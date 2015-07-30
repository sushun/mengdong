package cn.md.trainclient.response;

import cn.md.trainclient.request.ApiRequest;

/**
 * Author: sushun
 * Date: 2015-07-09.
 */
public class ApiSuccessResult extends RequestResult {
    private static final long serialVersionUID = 87123903367324751L;

    private ApiRequest request;
    private ApiResponse response;

    public ApiSuccessResult(ApiRequest request, ApiResponse respones) {
        this.request = request;
        this.response = respones;
    }

    public ApiRequest getRequest() {
        return request;
    }

    public ApiResponse getResponse() {
        return response;
    }
}
