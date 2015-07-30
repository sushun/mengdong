package cn.md.trainclient.response;

/**
 * Author: sushun
 * Date: 2015-07-09.
 */
public class NetErrResult extends RequestResult {
    private static final long serialVersionUID = 3985260224512206018L;

    private Exception exception;

    public NetErrResult(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
