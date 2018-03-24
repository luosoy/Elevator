
package com.luosoy.common.exception;

public class BusinessException extends BaseException {


    public static final String REQUEST_EXCEPTION = "000101";

    public static final String RESPONSE_EXCEPTION = "000102";

    public static final String JSON_EXCEPTION = "000203";

    public static final String REQUEST_PARAM_EXCEPTION = "000204";


    public BusinessException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public BusinessException(String message, String errorCode) {
        super(message, null, errorCode);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause, REQUEST_EXCEPTION);
    }

    public BusinessException(String message) {
        super(message, null, REQUEST_EXCEPTION);
    }
}
