package cn.md.trainclient.response;

import java.util.List;

import cn.md.trainclient.api.TestResponse;

/**
 * Created by RoyalFY on 2015/7/14.
 */
public class LoginResponse extends ApiResponse{
    private String code;
    private String name;
    private List<TestResponse> list;
}
