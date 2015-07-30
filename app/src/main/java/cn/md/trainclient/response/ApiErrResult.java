package cn.md.trainclient.response;

import cn.md.trainclient.api.ApiException;

/**
 * @author: sushun
 * Date: 2015-07-09.
 */
public class ApiErrResult extends RequestResult {
    private static final long serialVersionUID = 6081136153050741701L;

    private ApiException apiException;

    public ApiErrResult(ApiException apiException) {
        this.apiException = apiException;
    }

    public ApiException getApiException() {
        return apiException;
    }
}
