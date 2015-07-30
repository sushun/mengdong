package cn.md.trainclient.response;

import java.io.Serializable;
import cn.md.trainclient.api.ApiErrorCode;

/**
 * Author: sushun
 * Date: 2015-07-09.
 */
public class ApiResponse implements Response, Serializable {
    private static final long serialVersionUID = -6998281804342886762L;

    private int code;
    private String message;

    public boolean isSuccess() {
        return code == ApiErrorCode.NO_ERROR.getErrorCode();
    }

    public int getErrorcode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
