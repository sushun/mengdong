package cn.md.trainclient.api;

import cn.md.trainclient.request.ApiRequest;

public class TestRequest extends ApiRequest {
    private static final long serialVersionUID = 2092894098749806377L;
    @ApiField(paramName = "mobile")
    String param1;

    public TestRequest(String param1) {
        this.param1 = param1;
    }
}
