package cn.md.trainclient.request;

import cn.md.trainclient.api.ApiField;

/**
 * Created by RoyalFY on 2015/7/14.
 */
public class LoginRequest extends ApiRequest{

    @ApiField(paramName="myname")
    private String name;
    @ApiField(paramName="mypasssword")
    private String passWord;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
