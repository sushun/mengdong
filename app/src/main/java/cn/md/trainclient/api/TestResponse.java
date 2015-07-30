package cn.md.trainclient.api;

import cn.md.trainclient.response.ApiResponse;

public class TestResponse extends ApiResponse {
    private static final long serialVersionUID = -7993868114143328589L;

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
