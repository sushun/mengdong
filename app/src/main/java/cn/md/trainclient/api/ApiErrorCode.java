package cn.md.trainclient.api;

public enum ApiErrorCode {

    NO_ERROR(0),

    GSON_ERROR(8001),

    UNKNOWN(8002), ;

    private int errorCode;

    ApiErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
