package cn.md.trainclient.api;

import java.io.Serializable;

public class ApiException extends RuntimeException {
    public static class ApiError implements Serializable {

        private static final long serialVersionUID = 1L;
        private ApiErrorCode errorCode;
        private String errorMessage;

        ApiError(ApiErrorCode errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public ApiErrorCode getReasonCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        @Override
        public String toString() {
            return String.format("errorCode: %s, errorMessage: %s", errorCode, errorMessage);
        }
    }

    private static final long serialVersionUID = 1L;

    private final ApiError apiError;

    public ApiException(ApiErrorCode errorCode) {
        this(errorCode, null/* errorMessage */);
    }

    public ApiException(ApiErrorCode errorCode, String errorMessage) {
        this.apiError = new ApiError(errorCode, errorMessage);
    }

    public ApiError getApiError() {
        return apiError;
    }

    @Override
    public String toString() {
        return "apiError: " + apiError;
    }

    /**
     * Creates an {@link cn.eshifu.hclient.api.ApiException} from an error_code.
     */
    public static ApiException from(int errorCode) {
        for (final ApiErrorCode apiErrorCode : ApiErrorCode.values()) {
            if (apiErrorCode.getErrorCode() == errorCode) {
                return new ApiException(apiErrorCode);
            }
        }
        return new ApiException(ApiErrorCode.UNKNOWN, String.valueOf(errorCode));
    }
}
